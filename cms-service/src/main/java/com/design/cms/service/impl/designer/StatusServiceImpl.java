package com.design.cms.service.impl.designer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.design.cms.common.assist.DesignException;
import com.design.cms.common.enums.DesignEx;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Status;
import com.design.cms.dao.entity.dto.StatusInfo;
import com.design.cms.dao.persist.StatusMapper;
import com.design.cms.service.api.IStatusService;
import com.design.cms.service.api.vo.designer.StatusSearchVo;
import com.design.cms.service.impl.cmsuser.UploadServiceImpl;
import com.google.common.collect.Maps;
@Service
public class StatusServiceImpl implements IStatusService{
	
	@Resource
	private UploadServiceImpl uploadServiceImpl;
	@Resource
	private StatusMapper statusMapper;
	@Value("${upload.path}")
	private String uploadPath;
	
	
	@Override
	public int getCount(StatusSearchVo searchVo) {
		Map<String,Object> params = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getStatusTypeId())){
			params.put("statusTypeId", searchVo.getStatusTypeId());
		}
		if(StringUtils.isNotEmpty(searchVo.getStatusName())){
			params.put("statusName", searchVo.getStatusName());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			params.put("isEnable",Integer.valueOf(searchVo.getIsEnable()));
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
			params.put("designerId",Long.valueOf(searchVo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getThemeId())){
			params.put("themeId",Long.valueOf(searchVo.getThemeId()));
		}
		return statusMapper.getCount(params);
	}

	@Override
	public List<StatusInfo> getList(StatusSearchVo searchVo) {
		Map<String,Object> params = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getStatusTypeId())){
			params.put("statusTypeId", searchVo.getStatusTypeId());
		}
		if(StringUtils.isNotEmpty(searchVo.getStatusName())){
			params.put("statusName", searchVo.getStatusName());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			params.put("isEnable",Integer.valueOf(searchVo.getIsEnable()));
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerId())){
			params.put("designerId",Long.valueOf(searchVo.getDesignerId()));
		}
		if(StringUtils.isNotEmpty(searchVo.getThemeId())){
			params.put("themeId",Long.valueOf(searchVo.getThemeId()));
		}
		params.put("begin", searchVo.getBegin());
		params.put("end", searchVo.getEnd());
		return statusMapper.getList(params);
	}
	@Override
	public Long addStatus(Status status){
		String path = uploadPath+File.separator+"statusDetailText"+File.separator;
		String fileName = DateUtil.getCurrent(DateUtil.ALL_PATTERN)+new Random(UUID.randomUUID().hashCode()).nextInt(99)+".txt";
		FileOutputStream out;
		try {
			out = FileUtils.openOutputStream(new File(path+fileName));
			IOUtils.write(status.getStatusDetail(), out,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		status.setOrderBy(1);
		status.setStatusDetailUrl(fileName);
		status.setCreateTime(DateUtil.getCurrentDate());
		statusMapper.insert(status);
		return status.getId();
	}

	@Override
	public void updateStatus(Status status) {
		String path = uploadPath+File.separator+"statusDetailText"+File.separator;
		String fileName = status.getStatusDetailUrl();
		if(StringUtils.isEmpty(fileName)){
			fileName = DateUtil.getCurrent(DateUtil.ALL_PATTERN)+new Random(UUID.randomUUID().hashCode()).nextInt(99)+".txt";
		}
		if(StringUtils.isNotEmpty(status.getStatusDetail())){
			FileOutputStream out = null;;
			try {
				out = FileUtils.openOutputStream(new File(path+fileName));
				IOUtils.write(status.getStatusDetail(), out,"UTF-8");
			} catch (IOException e) {
				e.printStackTrace();
			}
			status.setStatusDetailUrl(fileName);
		}
		
		if(status.getPicture()!=null){
			File file = uploadServiceImpl.getFile(status.getPicture());
			BufferedImage bufferImg;
			try {
				bufferImg = ImageIO.read(file);
			} catch (IOException e) {
				throw new DesignException(e.getMessage());
			}
			status.setPictureWidth(bufferImg.getWidth());
			status.setPictureHeight(bufferImg.getHeight());
		}
		
		status.setUpdateTime(DateUtil.getCurrentDate());
		statusMapper.update(status);
	}

	@Override
	public String getStatusDetailTxt(String url){
		String path = uploadPath+File.separator+"statusDetailText"+File.separator;
		String content = null;
		try {
			content = FileUtils.readFileToString(new File(path+url),"UTF-8");
		} catch (IOException e) {
			throw new DesignException(DesignEx.INTERNAL_ERROR);
		}
		return content;
	}

}
