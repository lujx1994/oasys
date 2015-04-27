package com.yanoda.rbac.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yanoda.rbac.domain.Form;

public interface FormMapper {
	Form getForm(int formId);
	
	@Select("SELECT * FROM form")
	List<Form> getAllForm();
}
