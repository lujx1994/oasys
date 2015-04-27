package com.yanoda.rbac.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yanoda.rbac.domain.Role;
import com.yanoda.rbac.domain.User;
import com.yanoda.rbac.domain.Workflow;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PermissionCheckServiceTest {
	
	@Autowired
	private WorkflowService workflowService;
	@Autowired
	private PermissionCheckService permissionCheckService;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoCheckPermisson() {
		Workflow workflow = this.workflowService.getWorkflowDetail(10);
		int user_id = 3;
		User rbacUser = new User();
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setId(3);
		roles.add(role);
		rbacUser.setRoleList(roles);
		HashMap<Integer, User> rbac_users = new HashMap<Integer, User>();
		rbac_users.put(3, rbacUser);
		assertTrue(this.permissionCheckService.doCheckPermisson(workflow, user_id, rbac_users));
	}

}
