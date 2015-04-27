package com.yanoda.rbac.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WorkflowServiceTest {
	
	@Autowired
	private WorkflowService workflowService;
	
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
	public void testCreateWorkflow() {
		String workformName = "1asas";
		String role_flow = "ddd";
		String content = "ddd";
		int user_id = 3;
		String custom = "d";
		String date = "Thu Apr 23 19:00:42 CST 2015";
		assertTrue(this.workflowService.createWorkflow(workformName, role_flow, content.getBytes(), custom, user_id, date) == 1);
	}

	@Test
	public void testGetAllPendingWorkflowList() {
		assertTrue(this.workflowService.getAllPendingWorkflowList().get(0).getCreate_user_id() == 3);
	}

	@Test
	public void testGetWorkflowDetail() {
		assertTrue(this.workflowService.getWorkflowDetail(68).getCreate_user_id() == 2);
	}

	@Test
	public void testDoModifyWorkflow() {
		int flow_id = 1;
		String content = "f";
		String user_flow = "1212dsf";
		String role_flow = "121213dads";
		int status = 1;
		assertTrue(this.workflowService.doModifyWorkflow(flow_id, content.getBytes(), user_flow, role_flow, status) == 1);
	}

}
