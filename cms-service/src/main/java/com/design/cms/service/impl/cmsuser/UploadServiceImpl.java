package com.design.cms.service.impl.cmsuser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.dao.entity.Attachment;
import com.design.cms.dao.persist.AttachmentMapper;
import com.design.cms.service.api.vo.common.FileUploadResp;
import com.design.cms.service.impl.aliyun.AliyunServiceImpl;
import com.design.cms.service.impl.aliyun.ChicunAliFile;
@Service
public class UploadServiceImpl {
	@Resource
	private AttachmentMapper attachmentMapper;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@Value("${upload.temp.path}")
	private String uploadTempPath;
	
	@Resource
	private AliyunServiceImpl aliyunServiceImpl;

	public File getFile(Long id) {
		Attachment t_Attachment =  attachmentMapper.getAttachmentById(id);
		if(t_Attachment==null){
			throw new DesignException(DesignEx.ATTACHMENET_NOTFUND);
		}
		File file = new File(t_Attachment.getFilePath()+t_Attachment.getServerFileName());
		if(file.exists()){
			return file;
		}
		throw new DesignException(DesignEx.FILE_NOTEXISTS);
	}
	
	@Transactional
	public FileUploadResp uploadFile(MultipartFile file, String classify,boolean temp) {
		if(file.isEmpty()){
			return null;
		}
		String originalfileName = file.getOriginalFilename();
		String suffix = file.getOriginalFilename().substring(originalfileName.lastIndexOf("."), originalfileName.length());
		String newName = DateUtil.getCurrent(DateUtil.ALL_PATTERN)+new Random(UUID.randomUUID().hashCode()).nextInt(99)+suffix;
		String filepath = null;
		if(temp){
			filepath = uploadTempPath+classify+File.separator;
		}else{
			filepath = uploadPath+classify+File.separator;
		}
		
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filepath+newName));
		} catch (IOException e) {
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}
		
		Attachment attachment = new Attachment();
		attachment.setFileName(originalfileName);
		attachment.setFilePath(filepath);
		attachment.setServerFileName(newName);
		attachment.setClassify(classify);
		attachment.setCreateTime(DateUtil.getCurrentDate());
		attachmentMapper.insert(attachment);
		
		if(!temp){
			aliyunServiceImpl.uploadFiles(new ChicunAliFile(filepath+newName, String.valueOf(attachment.getId())));
		}
		
		FileUploadResp fileUploadResp = new FileUploadResp();
		fileUploadResp.setAttachmentId(attachment.getId());
		fileUploadResp.setClassify(classify);
		fileUploadResp.setFileName(originalfileName);
		fileUploadResp.setFilePath(filepath+File.separator+newName);
		fileUploadResp.setServerFileName(newName);
		return fileUploadResp;
	}
	
	@Transactional
	public void moveTempFileToFormal(Long attachmentId){
		if(attachmentId==null){
			return ;
		}
		try {
			Attachment attachment = attachmentMapper.getAttachmentById(attachmentId);
			if(attachment!=null){
				if(attachment.getFilePath().contains("temp")){
					File file = new File(attachment.getFilePath()+attachment.getServerFileName());
					String filePath = uploadPath+attachment.getClassify()+File.separator;
					FileUtils.copyInputStreamToFile(new FileInputStream(file), new File(filePath+attachment.getServerFileName()));
					attachment.setFilePath(filePath);
					attachmentMapper.update(attachment);
					aliyunServiceImpl.uploadFiles(new ChicunAliFile(filePath+attachment.getServerFileName(), String.valueOf(attachment.getId())));
				}
			}
		} catch (IOException e) {
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}
	}

	public Long kindEditoruploadImgFile(MultipartFile file) {
		if(file.isEmpty()){
			return null;
		}
		String originalfileName = file.getOriginalFilename();
		String suffix = file.getOriginalFilename().substring(originalfileName.lastIndexOf("."), originalfileName.length());
		String newName = DateUtil.getCurrent(DateUtil.ALL_PATTERN)+new Random(UUID.randomUUID().hashCode()).nextInt(99)+suffix;
		String classify = "status";
		String filepath = uploadPath+classify+File.separator;
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(filepath+newName));
		} catch (IOException e) {
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}
		Attachment attachment = new Attachment();
		attachment.setFileName(originalfileName);
		attachment.setFilePath(filepath);
		attachment.setServerFileName(newName);
		attachment.setClassify(classify);
		attachment.setCreateTime(DateUtil.getCurrentDate());
		attachmentMapper.insert(attachment);
		aliyunServiceImpl.uploadFiles(new ChicunAliFile(filepath+newName, String.valueOf(attachment.getId())));
		return attachment.getId();
	}

}
