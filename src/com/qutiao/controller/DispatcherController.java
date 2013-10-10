package com.qutiao.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qutiao.domain.QutiaoDispatcher;
import com.qutiao.service.IDispatcherService;
import com.qutiao.util.UAUtil;

@Controller
public class DispatcherController {
	@Autowired
	private IDispatcherService dispatcherService;

	@RequestMapping("/goto/{dispatcherId}.html")
	public String redirectUrl(HttpServletRequest request,
			@PathVariable("dispatcherId") long dispatcherId) {
		String url = "";
		QutiaoDispatcher dispatcher = dispatcherService
				.searchDispatcherById(dispatcherId);
		if (dispatcher == null)
			return "redirect:/error.html";
		if (UAUtil.isFromMobile(request))
			url = dispatcher.getPhoneUrl();
		else
			url = dispatcher.getWebUrl();
		return "redirect:" + url;
	}
}
