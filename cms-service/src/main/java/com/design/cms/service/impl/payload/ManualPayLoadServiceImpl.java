package com.design.cms.service.impl.payload;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.design.cms.common.enums.PayLoadType;
import com.design.cms.dao.entity.Theme;
import com.design.cms.service.api.IPayLoadService;
import com.design.cms.service.api.IPayLoadStrategy;
import com.design.cms.service.api.IThemeService;
import com.design.cms.service.api.IUserService;
import com.design.cms.service.api.vo.payload.PayLoad;
@Service
public class ManualPayLoadServiceImpl {
	
	@Resource
	private IPayLoadService payLoadService;
	@Resource
	private IUserService userServiceImpl;
	@Resource
	private IThemeService themeServiceImpl;
	
	public void doUserDefined(final PayLoad vo){
		payLoadService.exec(new IPayLoadStrategy(){

			@Override
			public PayLoad getPayLoad() {
				PayLoad payLoad = new PayLoad();
				payLoad.setPayLoadType(PayLoadType.DEFAULT.getTypeCode());
				payLoad.setTitle(vo.getTitle());
				payLoad.setBody(vo.getBody());
				payLoad.setDeviceTokens(getAllUserDeviceTokens());
				return payLoad;
			}
			
		});
	}
	
	public void newGoodsExec(Long themeId){
		payLoadService.exec(new NewGoodsStrategy(themeId));
	};
	
	
	public List<String> getAllUserDeviceTokens(){
		List<String> deviceTokens = userServiceImpl.getAllUserDeviceTokens();
		return deviceTokens;
	}
	
	private class NewGoodsStrategy implements IPayLoadStrategy{
		private Long themeId;
		
		public NewGoodsStrategy(Long themeId) {
			super();
			this.themeId = themeId;
		}

		@Override
		public PayLoad getPayLoad() {
			Theme t_themeInfo = themeServiceImpl.getThemeById(themeId);
			PayLoad payLoad = new PayLoad();
			payLoad.setPayLoadType(PayLoadType.NEWGOODS.getTypeCode());
			payLoad.setTitle("新品上架");
			payLoad.setBody(t_themeInfo.getThemeName());
			payLoad.setIdntify(themeId.toString());
			payLoad.setDeviceTokens(getAllUserDeviceTokens());
			return payLoad;
		}
	}
}
