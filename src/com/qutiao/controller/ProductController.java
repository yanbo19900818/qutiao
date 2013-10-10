package com.qutiao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProduct;
import com.qutiao.service.IProductService;
import com.qutiao.vo.ProductDetail;

@Controller
public class ProductController {
	@Autowired
	IProductService productService;

	@RequestMapping("/detail_{id}_{reviewPage}.html")
	public String searchDetail(@PathVariable("id") long id,
			@PathVariable("reviewPage") int reviewPage, ModelMap map) {
		ProductDetail product = productService.searchDetail(id, reviewPage);
		product.setContent(product.getContent().replaceAll("xgsize", "640x480"));
		Page rightPage = productService.searchAll(5, 1);
		List<QutiaoProduct> interestedList=productService.randomProduct(3);
		map.put("product", product);
		map.put("rightPage", rightPage);
		map.put("interestedList", interestedList);
		return "detail";
	}

	@RequestMapping("/new.html")
	public String searchNewProduct(ModelMap map) {
		Page page = productService.searchAll(5, 1);
		map.put("list", page.getResult());
		return "new";
	}

	@RequestMapping("/agreeProduct.do")
	@ResponseBody
	public String agreeProduct(long id) {
		boolean flag = productService.agreeProduct(id);
		if (flag)
			return "{rs:1}";
		else
			return "{rs:0}";
	}

	@RequestMapping("/disagreeProduct.do")
	@ResponseBody
	public String disagreeProduct(long id) {
		boolean flag = productService.disagreeProduct(id);
		if (flag)
			return "{rs:1}";
		else
			return "{rs:0}";
	}
	
}
