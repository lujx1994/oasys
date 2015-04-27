package com.yanoda.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Workflow;
import com.yanoda.rbac.mapper.WorkflowMapper;
import com.yanoda.rbac.service.WorkflowService;

@Service
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private WorkflowMapper workflowMapper;

	@Override
	public int createWorkflow(String workformName, String role_flow,
			byte[] content, String custom, int user_id, String date) {
		int count = 0;
		try {
			count = this.workflowMapper.createWorkflow(workformName,
					role_flow, custom, content, user_id, date);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("增加工作流失败");
		}
		return count;
	}

	// 查询所有待处理的工作流
	@Override
	public List<Workflow> getAllPendingWorkflowList() {
		// TODO Auto-generated method stub
		return this.workflowMapper.getAllPendingWorkflowList();
	}

	// the details of workflow
	@Override
	public Workflow getWorkflowDetail(int flow_id) {
		// TODO Auto-generated method stub
		return this.workflowMapper.getWorkflowDetail(flow_id);
	}

	// 更新工作流
	@Override
	public int doModifyWorkflow(int flow_id, byte[] content, String user_flow,
			String role_flow, int status) {
		return this.workflowMapper.doModifyWorkflow(flow_id, content,
				user_flow, role_flow, status);
	}

	@Override
	public int updateStatusById(int id) {
		// TODO Auto-generated method stub
		return this.workflowMapper.updateStatusById(id);
	}

}
