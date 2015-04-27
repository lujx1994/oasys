package com.yanoda.rbac.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Form;
import com.yanoda.rbac.mapper.FormMapper;
import com.yanoda.rbac.service.FormService;

@Service
public class FormServiceImpl implements FormService {
	@Autowired
	private FormMapper formMapper;

	@Override
	public Form getForm(int formId) {
		// TODO Auto-generated method stub
		return this.formMapper.getForm(formId);
	}

	@Override
	public List<Form> getAllForm() {
		// TODO Auto-generated method stub
		return this.formMapper.getAllForm();
	}
	
	
}
