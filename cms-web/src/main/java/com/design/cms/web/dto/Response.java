package com.design.cms.web.dto;

import com.design.cms.common.assist.Constant;
import com.design.cms.service.api.PageVo;

public class Response<T>{
	/**
	 * 是否成功，true为成功，false为失败
	 */
	private boolean success;
	private String respCode;
	private String respMessage;
	private T result;
	private PageVo pageVo;
	public Response(){
		
	}
	public Response(T result){
		this.success = true;
		this.result = result;
	}
	public boolean isSuccess() {
		return success;
	}
	
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMessage() {
		return respMessage;
	}
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.respCode = Constant.SUCCESS;
		this.success = true;
		this.result = result;
	}
	public PageVo getPageVo() {
		return pageVo;
	}
	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
}
