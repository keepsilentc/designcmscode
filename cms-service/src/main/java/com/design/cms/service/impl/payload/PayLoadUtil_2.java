package com.design.cms.service.impl.payload;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dbay.apns4j.IApnsService;
import com.dbay.apns4j.impl.ApnsServiceImpl;
import com.dbay.apns4j.model.ApnsConfig;
import com.dbay.apns4j.model.Payload;
import com.design.cms.common.assist.DesignException;

@Service
public class PayLoadUtil_2 {
	
	private static Logger log = LoggerFactory.getLogger(PayLoadUtil_2.class);
	
	private static IApnsService apnsService;
	
	@Value("${keystore.path}")
	private String keyStoreMeta;
	
	@Value("${apns.apnsDevEnv}")
	private boolean apnsDevEnv;
	
	@Value("${keystore.password}")
	private String keyStorePwd;
	
	private IApnsService getApnsService() {
		if (apnsService == null) {
			ApnsConfig config = new ApnsConfig();
			InputStream is = PayLoadUtil_2.class.getClassLoader().getResourceAsStream(keyStoreMeta);
			config.setKeyStore(is);
			config.setDevEnv(apnsDevEnv);
			config.setPassword(keyStorePwd);
			config.setPoolSize(3);
			// 假如需要在同个java进程里给不同APP发送通知，那就需要设置为不同的name
			config.setName("welove1");
			apnsService = ApnsServiceImpl.createInstance(config);
		}
		return apnsService;
	}
	
	
	
	
	public void send(Payload payload,String ...deviceTokens){
		IApnsService service = getApnsService();
		try{
			for(String deviceToken:deviceTokens){
				log.info("推送：{}",deviceToken);
				service.sendNotification(deviceToken, payload);
			}
		} catch (Exception e) {
			throw new DesignException(e.getMessage());
		}finally{
			service.shutdown();
		}
	}
	
	
}
