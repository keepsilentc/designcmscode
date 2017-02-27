package com.design.cms.service.impl.designer;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.design.cms.common.assist.Constant;
import com.design.cms.common.utils.DateUtil;
import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.Designer;
import com.design.cms.dao.entity.dto.DesignerInfo;
import com.design.cms.dao.persist.DesignerMapper;
import com.design.cms.dao.persist.UserDesignerMapper;
import com.design.cms.service.api.IDesignerService;
import com.design.cms.service.api.vo.designer.DesignerReq;
import com.design.cms.service.api.vo.designer.DesignerSearchVo;
import com.design.cms.service.impl.cmsuser.UploadServiceImpl;
import com.google.common.collect.Maps;
@Service
public class DesignerServiceImpl implements IDesignerService{
	private static Logger log = LoggerFactory.getLogger(DesignerServiceImpl.class);
	@Resource
	private DesignerMapper designerMapper;
	@Resource
	private UserDesignerMapper userdesignerMapper;
	@Resource
	private UploadServiceImpl uploadServiceImpl;
	@Override
	public int getCount(DesignerSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getCountryId())){
			param.put("countryId", searchVo.getCountryId());
		}
		if(StringUtils.isNotEmpty(searchVo.getBrandId())){
			param.put("brandId", searchVo.getBrandId());
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerName())){
			param.put("designerName", searchVo.getDesignerName());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("isEnable",Integer.valueOf(searchVo.getIsEnable()));
		}
		return designerMapper.getCount(param);
	}
	@Override
	public List<DesignerInfo> getList(DesignerSearchVo searchVo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(searchVo.getCountryId())){
			param.put("countryId", searchVo.getCountryId());
		}
		if(StringUtils.isNotEmpty(searchVo.getBrandId())){
			param.put("brandId", searchVo.getBrandId());
		}
		if(StringUtils.isNotEmpty(searchVo.getDesignerName())){
			param.put("designerName", searchVo.getDesignerName());
		}
		if(StringUtils.isNotEmpty(searchVo.getIsEnable())){
			param.put("isEnable",Integer.valueOf(searchVo.getIsEnable()));
		}
		param.put("begin", searchVo.getBegin());
		param.put("end", searchVo.getEnd());
		return designerMapper.getList(param);
	}
	
	@Override
	public int delDesigner(Long id) {
		log.info("delDesigner****");
		return designerMapper.delDesignerById(id);
	}
	
	@Override
	public List<DesignerInfo> getAllDesigner(DesignerReq req) {
		Map<String,String> param = Maps.newHashMap();
		if(null!=req){
			if(StringUtils.isNotEmpty(req.getCountryId())){
				param.put("countryId", req.getCountryId());
			}
			if(StringUtils.isNotEmpty(req.getBrandId())){
				param.put("brandId", req.getBrandId());
			}
		}
		return designerMapper.getDesignersByCondition(param);
	}
	@Override
	public Long addDesigner(Designer designer) {
		designer.setIsEnable(Constant.UNENABLE);
		designer.setBrandId("BRAND0000");
		designer.setCreateTime(DateUtil.getCurrentDate());
		designerMapper.insert(designer);
		return designer.getId();
	}
	@Override
	public void updateDesigner(Designer designer) {
		designerMapper.update(designer);
	}
	@Override
	public List<Map<String, Object>> getExportData(DesignerSearchVo vo) {
		Map<String,Object> param = Maps.newHashMap();
		if(StringUtils.isNotEmpty(vo.getCountryId())){
			param.put("countryId", vo.getCountryId());
		}
		if(StringUtils.isNotEmpty(vo.getBrandId())){
			param.put("brandId", vo.getBrandId());
		}
		if(StringUtils.isNotEmpty(vo.getDesignerName())){
			param.put("designerName", vo.getDesignerName());
		}
		if(StringUtils.isNotEmpty(vo.getIsEnable())){
			param.put("isEnable",Integer.valueOf(vo.getIsEnable()));
		}
		return designerMapper.getExportData(param);
	}
	
}
