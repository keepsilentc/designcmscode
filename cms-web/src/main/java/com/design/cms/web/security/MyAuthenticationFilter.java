package com.design.cms.web.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.design.cms.common.utils.StringUtils;
import com.design.cms.dao.entity.CmsUser;
import com.design.cms.dao.persist.CmsUserMapper;

public class MyAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	/**
	 * 登录成功后跳转的地址
	 */
	private String successUrl = "/product/index.do";
	/**
	 * 登录失败后跳转的地址
	 */
	private String errorUrl = "/index.do";
	@Resource
	private CmsUserMapper cmsUserMapper;
	
	public void init() {
//		System.err.println(" ---------------  MyAuthenticationFilter init--------------- ");
		this.setUsernameParameter(USERNAME);
		this.setPasswordParameter(PASSWORD);
		// 验证成功，跳转的页面
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);
		this.setAuthenticationSuccessHandler(successHandler);

		// 验证失败，跳转的页面
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不能为空！");// 在界面输出自定义的信息！！
			throw exception;
		}

		// 验证用户账号与密码是否正确
		CmsUser t_cmsUser = cmsUserMapper.getUserByUserName(username);
		if (t_cmsUser == null || !t_cmsUser.getUserPassword().equals(password)) {
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不匹配！");// 在界面输出自定义的信息！！
			throw exception;
		}
		// 当验证都通过后，把用户信息放在session里
		request.getSession().setAttribute("userSession", t_cmsUser);
		
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
}
