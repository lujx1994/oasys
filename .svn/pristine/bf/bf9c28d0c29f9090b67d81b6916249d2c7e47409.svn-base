package com.yanoda.rbac.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yanoda.rbac.domain.Page;
import com.yanoda.rbac.mapper.PageMapper;
import com.yanoda.rbac.service.PaginationService;

@Service
public class PaginationServiceImpl implements PaginationService {
	
	@Autowired
	private PageMapper pageMapper;
	
	@Override
	public Page PaginationUtil(Integer nowPage, Integer pageSize, String tableName, String where) {
		
		//initialise page
		Page page = new Page();
		page.setNowPage(nowPage);
		page.setPageSize(pageSize);
		page.setTableName(tableName);
		page.setWhere(where);
		
		//if now page < 1, then make it be 1;
		if (page.getNowPage() < 1) {
			page.setNowPage(1);
		}
		//calcalutor the number of total rows
		page.setTotal(this.pageMapper.getCountSize(tableName, where));
		//calculator the numbet of total pages
		page.setCountPage(page.getTotal() % page.getPageSize() == 0 ? page.getTotal()
				/ page.getPageSize() : page.getTotal() / page.getPageSize() + 1);
		//if now page > the number of total pages, then set the last page as now page
		if (page.getNowPage() > page.getCountPage()) {
			page.setNowPage(page.getCountPage());
		}
		//set start index = (now page - 1) * the size of page
		page.setStartIndex((page.getNowPage() - 1) * page.getPageSize());
		//set end index = the size of page
		page.setEndIndex(page.getPageSize());
		return page;
	}
	
	@Override
	public List<HashMap<String, Object>> getRows(String[] columns, String tableName, String where) {
		StringBuffer str = new StringBuffer();
	    for(int i = 0; i < columns.length; i++){
	    	if(i+1 < columns.length) {
	    		str. append(columns[i]+',');
	    	}else {
	    		str. append(columns[i]);
	    	}
	    }
	    String selectColumns = str.toString();
    	return this.pageMapper.getRows(selectColumns, tableName, where);
	}
	
}