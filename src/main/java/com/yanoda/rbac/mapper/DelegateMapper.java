package com.yanoda.rbac.mapper;

import java.util.List;

import com.yanoda.rbac.domain.Delegate;

public interface DelegateMapper {

	List<Delegate> selectDelegate(int user_id);

}
