package com.yanoda.rbac.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Permission;
import com.yanoda.rbac.domain.Role;
import com.yanoda.rbac.domain.User;
import com.yanoda.rbac.domain.Workflow;
import com.yanoda.rbac.mapper.UserMapper;
import com.yanoda.rbac.service.PermissionCheckService;

@Service
public class PermissionCheckServiceImpl implements PermissionCheckService {
	
	@Autowired
	private UserMapper userMapper;
	
	
	public boolean doCheckPermisson(Workflow workflow, int user_id, HashMap<Integer, User> rbac_users) {
		boolean check = false;
		// get the roleflow in this workflow
		String roleflow = workflow.getRole_flow();
		// get the first in the roleflow
		if (roleflow.split(",").length <= 1) {
			return false;
		}
		int flowRoleId = Integer.valueOf(roleflow.split(",")[1]);
		// if the workflow belongs to roleflow
		if (workflow.getCustom().equals("f")) {
			try {
				// if i have this role
				if (rbac_users.get(user_id) == null) {
					return false;
				}
				List<Role> roles = rbac_users.get(user_id).getRoleList();
				for (Role role : roles) {
					System.out.println("print role and flowRoleId" + role.getId() + "vs" + flowRoleId);
					if (role.getId() == flowRoleId) {
						System.out.println("it is really some one role_id == flowRoleId");
						check = true;
						//get the info of the create_user
						User workflowUser = this.userMapper.getUserById(workflow.getCreate_user_id());
						if (check == false) {
							check = doCheckSomeOneCan(flowRoleId, workflow, workflowUser, rbac_users);
						}
					}
				}
				
			} catch (NullPointerException e) {
				e.printStackTrace();
				check = false;
			}
		} else {
			//if the workflow belongs to user-defined flow and my id equals the first id, then get the permission
			if (user_id == flowRoleId) {
				check = true;
			}
		}
		return check;
	}
	
	public boolean doCheckSomeOneCan(int flowRoleId, Workflow workflow,
			User workflowAccount, HashMap<Integer, User> rbac_users) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doCheckPermissionRole(int user_id,
			HashMap<Integer, User> rbac_users,
			HashMap<Integer, Role> rbac_actions) {
		// TODO Auto-generated method stub
		try {
			if (rbac_users.get(user_id) == null) {
				return false;
			}
			List<Role> roles = rbac_users.get(user_id).getRoleList();
			for (Role role : roles) {
				System.out.println("print role and flowRoleId" + role.getId());
				List<Permission> permissionList = rbac_actions.get(role.getId()).getPermissionList();
				for (Permission permission : permissionList) {
					if (permission.getId() == 4) {
						return true;
					}
					return false;
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}
}


