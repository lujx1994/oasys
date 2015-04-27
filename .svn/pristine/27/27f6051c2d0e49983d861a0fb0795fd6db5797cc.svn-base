package com.yanoda.rbac.service;

import java.util.HashMap;

import com.yanoda.rbac.domain.Role;
import com.yanoda.rbac.domain.User;
import com.yanoda.rbac.domain.Workflow;

public interface PermissionCheckService {
	public boolean doCheckPermisson(Workflow workflow, int user_id, HashMap<Integer, User> rbac_users);
	public boolean doCheckPermissionRole(int user_id, HashMap<Integer, User> rbac_users, HashMap<Integer, Role> rbac_actions);
}
