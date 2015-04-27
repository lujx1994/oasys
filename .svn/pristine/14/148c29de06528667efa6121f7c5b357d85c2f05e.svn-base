package com.yanoda.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yanoda.rbac.domain.Workflow;

public interface WorkflowMapper {

	@Insert("INSERT INTO workflow (name, role_flow, content, "
			+ "custom, create_user_id, create_time) "
			+ "VALUES (#{workformName}, #{role_flow}, "
			+ "#{content}, #{custom}, #{create_user_id}, #{create_time})")
	int createWorkflow(@Param("workformName") String workformName,
			@Param("role_flow") String role_flow,
			@Param("custom") String custom, @Param("content") byte[] content,
			@Param("create_user_id") int create_user_id,
			@Param("create_time") String date);

	@Select("SELECT* FROM workflow WHERE status=0")
	List<Workflow> getAllPendingWorkflowList();

	Workflow getWorkflowDetail(int flow_id);

	@Insert("UPDATE workflow SET user_flow=#{user_flow}, "
			+ "content=#{content}, role_flow=#{role_flow}, "
			+ "status=#{status} WHERE id=#{flow_id}")
	int doModifyWorkflow(@Param("flow_id") int flow_id,
			@Param("content") byte[] content,
			@Param("user_flow") String user_flow,
			@Param("role_flow") String role_flow, @Param("status") int status);
	
	@Update("UPDATE workflow SET status = 1 WHERE id = #{id}")
	int updateStatusById(@Param("id") int id);
}
