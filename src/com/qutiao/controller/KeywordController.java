package com.qutiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qutiao.dao.common.Page;
import com.qutiao.service.IKeywordService;
import com.qutiao.service.IProductService;

@Controller
public class KeywordController {
	@Autowired
	IKeywordService keywordService;
	@Autowired
	IProductService productService;

	@RequestMapping("/keyword_{kid}_{pageNum}.html")
	public String searchProductBykid(@PathVariable("kid") long kid,
			@PathVariable("pageNum") int pageNum, ModelMap map) {
		Page page = productService.searchProductByKeywordId(kid, pageNum, 5);
		map.put("keywordId", kid);
		map.put("page", page);
		return "search_result";
	}

	@RequestMapping("/keyword.do")
	@ResponseBody
	public String searchProductByKeyword(String signature, String timestamp,
			String nonce, String echostr) {
		return " <xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>12345678</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[content]]></Content><FuncFlag>0</FuncFlag></xml>";
	}
}
