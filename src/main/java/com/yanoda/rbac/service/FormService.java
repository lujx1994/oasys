package com.yanoda.rbac.service;

import java.util.List;

import com.yanoda.rbac.domain.Form;

public interface FormService {
	public Form getForm(int formId);
	public List<Form> getAllForm();
}
