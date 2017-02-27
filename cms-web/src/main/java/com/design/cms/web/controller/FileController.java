package com.design.cms.web.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.TraceLogIdUtils;
import com.design.cms.service.api.vo.common.FileUploadResp;
import com.design.cms.service.api.vo.common.KindEditorFileUploadResp;
import com.design.cms.service.impl.cmsuser.UploadServiceImpl;
import com.design.cms.web.dto.Response;
import com.google.common.base.Throwables;

@RequestMapping("/file")
@Controller
public class FileController {
	
	private static Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Resource
	private UploadServiceImpl uploadServiceImpl;
	
	@RequestMapping("/get")
	public void getFile(HttpServletResponse resp,@RequestParam(value="id",required=true) Long id){
		TraceLogIdUtils.setTraceLogId(null);
		if(id==null){
			return;
		}
		try {
			File file = uploadServiceImpl.getFile(id);
			FileUtils.copyFile(file, resp.getOutputStream());
		} catch (Exception e) {
			log.info(Throwables.getStackTraceAsString(e));
		}
	}
	
	@RequestMapping("/kindeditorupload")
	@ResponseBody
	public KindEditorFileUploadResp kindEditoruploadImgFile(HttpServletRequest request,@RequestParam(value="imgFile")MultipartFile file){
		TraceLogIdUtils.setTraceLogId(null);
		KindEditorFileUploadResp fileUploadResp = new KindEditorFileUploadResp();
		try{
			log.info("kindEditor上传文件接口执行...");
			Long attachmentId = uploadServiceImpl.kindEditoruploadImgFile(file);
			StringBuilder url = new StringBuilder();
			url.append(request.getContextPath());
			url.append("/file/get.do?id=");
			url.append(attachmentId);
			fileUploadResp.setUrl(url.toString());
		}catch(Exception e){
			String msg = Throwables.getStackTraceAsString(e);
			log.info("kindEditor上传文件接口执行异常,{}",msg);
			fileUploadResp.setError(1);
			fileUploadResp.setMessage(msg);
		}
		return fileUploadResp;
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public Response<FileUploadResp> uploadFile(@RequestParam(value="file")MultipartFile file,@RequestParam(value="classify",required=true) String classify,@RequestParam(value="temp",required=false) boolean temp){
		TraceLogIdUtils.setTraceLogId(null);
		Response<FileUploadResp> resp = new Response<FileUploadResp>();
		try{
			log.info("上传文件接口执行...classify:{},temp:{}",classify,temp);
			FileUploadResp fileUploadResp = uploadServiceImpl.uploadFile(file,classify,temp);
			resp.setResult(fileUploadResp);
		}catch(DesignException ex){
			log.info("上传文件接口执行异常,{}:{}",ex.getErrCode(),ex.getMessage());
			resp.setRespCode(ex.getErrCode());
			resp.setRespMessage(ex.getMessage());
		}catch(Exception e){
			log.info("上传文件接口执行异常,{}",Throwables.getStackTraceAsString(e));
			resp.setRespCode(DesignEx.INTERNAL_ERROR.getErrCode());
			resp.setRespMessage(DesignEx.INTERNAL_ERROR.getErrMsg());
		}
		return resp;
	}
}
