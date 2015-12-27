package com.itee.tsd.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itee.tsd.dto.IteeUserSessionBean;
import com.itee.tsd.entity.IteeUser;
import com.itee.tsd.service.IteeUserService;
import com.itee.tsd.utils.Constants;

/**
 * 
 * @author Jia Qianpeng
 * 2015-12-25
 */

@Controller
@RequestMapping ("/iteelogin/")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Resource
	private IteeUserService iteeUserService;

	@RequestMapping ("login")
	public String login(Model model, HttpServletRequest request, String notifyUrl) {
		if (StringUtils.isNotEmpty(notifyUrl)) {
			model.addAttribute("notifyUrl", notifyUrl);
		}
		return "/iteelogin/login";
	}
	
	@RequestMapping ("relogin")
	public String relogin(Model model, HttpServletRequest request, String notifyUrl) {
		if (StringUtils.isNotEmpty(notifyUrl)) {
			model.addAttribute("notifyUrl", notifyUrl);
		}
		return "/iteelogin/login";
	}
	
	@RequestMapping ("logout")
	public String logout(Model model,HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(Constants.SYS_USER_INF_IN_SESSION);
		request.getSession().invalidate();
		return "/iteelogin/login";
	}
	
	@RequestMapping ("loginCheck")
	public String loginCheck(Model model, String username, String password, String notifyUrl, 
			HttpServletRequest request) {
		try {
			if (!request.getMethod().equals("POST")) {
				model.addAttribute("error_msg", "不支持的方法提交！");
				return gotoLogin(model, notifyUrl);
			}
			if (StringUtils.isBlank(StringUtils.trimToEmpty(username))) {
				model.addAttribute("error_msg", "请输入账号！");
				return gotoLogin(model, notifyUrl);
			} 
			if (StringUtils.isBlank(StringUtils.trimToEmpty(password))) {
				model.addAttribute("error_msg", "请输入密码！");
				return gotoLogin(model, notifyUrl);
			}
			IteeUser iUser = iteeUserService.getByCredentials(username, password);
			if (iUser == null || iUser.getId() == null) {
				model.addAttribute("error_msg", "账号或密码错误！");
				return gotoLogin(model, notifyUrl);
			} else {
			    IteeUserSessionBean iUserBean = new IteeUserSessionBean();
			    iUserBean.setIteeUserId(iUser.getId());
			    iUserBean.setUsername(iUser.getUsername());
			    request.getSession(true).setAttribute(Constants.SYS_USER_INF_IN_SESSION, iUserBean);
			    iteeUserService.increaseLonginUserNum(iUser.getId());
			}
		} catch (Throwable e) {  
			log.error("登陆错误", e);
			request.setAttribute("error", "登录异常，请联系管理员！");
			return gotoLogin(model, notifyUrl);
		}
		if (StringUtils.isNotEmpty(notifyUrl)) {
			return "redirect:"+notifyUrl;
		}
		return "redirect:/sm/shirt-state.htm";
	}
	
	private String gotoLogin(Model model, String notifyUrl) {
		if (StringUtils.isNotEmpty(notifyUrl)) {
			model.addAttribute("notifyUrl", notifyUrl);
		}
		return "/iteelogin/login";
	}
}
