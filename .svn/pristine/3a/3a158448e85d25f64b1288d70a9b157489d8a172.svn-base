package com.yanoda.rbac.service;

import java.util.List;

import com.yanoda.rbac.domain.Workflow;

public interface WorkflowService {
	int createWorkflow(String workformName, String role_flow, 
			byte[] content, String custom, int user_id, String date);
	List<Workflow> getAllPendingWorkflowList();
	Workflow getWorkflowDetail(int flow_id);
	int doModifyWorkflow(int flow_id, byte[] content, String user_flow,
			String role_flow, int status);
	int updateStatusById(int id);
}
