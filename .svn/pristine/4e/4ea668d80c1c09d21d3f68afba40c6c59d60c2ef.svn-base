package com.yanoda.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.FormType;
import com.yanoda.rbac.mapper.FormTypeMapper;
import com.yanoda.rbac.service.FormTypeService;

@Service
public class FormTypeServiceImpl implements FormTypeService {
	
	@Autowired
	private FormTypeMapper formTypeMapper;

	@Override
	public List<FormType> getFormType() {
		return this.formTypeMapper.getFormType();
	}

}
