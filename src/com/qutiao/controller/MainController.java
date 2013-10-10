package com.qutiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qutiao.dao.common.Page;
import com.qutiao.service.IProductService;

@Controller
public class MainController {
@Autowired
	IProductService productService;

	@RequestMapping("/index.html")
	public String createIndex(ModelMap map) {
		Page page = productService.searchAll(20, 1);
		map.put("page", page);
		map.put("rightPage", page);
		return "index";
	}

	@RequestMapping("/index_{pageNum}.html")
	public String createIndexByPage(ModelMap map,@PathVariable("pageNum")int pageNum) {
		Page page = productService.searchAll(20, pageNum);
		Page rightPage = productService.searchAll(5, 1);
		map.put("page", page);
		map.put("rightPage", rightPage);
		return "index";
	}
}
