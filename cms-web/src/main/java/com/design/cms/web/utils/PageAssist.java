package com.design.cms.web.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.design.cms.service.api.PageOperation;
import com.design.cms.service.api.PageVo;
import com.design.cms.web.dto.Response;

@Service
public class PageAssist{

	public <T extends PageVo,U> void  queryList(PageOperation<T,U> pageOperation,T bean) {
		HttpServletRequest req = Evn.getRequest();
		req.setAttribute("searchVo", bean);
		int count = pageOperation.getCount(bean);
		if(count==0){
			return ;
		}
		int sumpage = count/bean.getPageSize();
		bean.setSumPage(count%bean.getPageSize()>0?sumpage+1:sumpage);
		if(count<=bean.getBegin()){
			bean.setCurrent(bean.getCurrent()-1);
		}
		req.setAttribute("resultCount", count);
		req.setAttribute("resultList", pageOperation.getList(bean));
		req.setAttribute("page", bean);
		
	}

	public <T extends PageVo,U> void pageQuery(PageOperation<T,U> pageOperation,T bean,Response<List<U>> resp) {
		int count = pageOperation.getCount(bean);
		if(count==0){
			bean.reset();
			bean.setTotal(count);
			resp.setPageVo(bean);
			resp.setResult(new ArrayList<U>());
			return ;
		}
		int sumpage = count/bean.getPageSize();
		bean.setSumPage(count%bean.getPageSize()>0?sumpage+1:sumpage);
		if(count<=bean.getBegin()){
			bean.setCurrent(bean.getCurrent()-1);
		}
		bean.setTotal(count);
		resp.setResult(pageOperation.getList(bean));
		resp.setPageVo(bean);
	}
	
}
