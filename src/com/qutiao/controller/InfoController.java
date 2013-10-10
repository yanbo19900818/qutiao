package com.qutiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoProduct;
import com.qutiao.service.IProductService;
import com.qutiao.util.Constant;

@Controller
public class InfoController {

    @Autowired
    private IProductService productService;
    
    @RequestMapping("/bg/info/verifyInfoList{page}.html")
    public String getVerifyInfoList(@PathVariable("page") int page,ModelMap modelMap){
        Page verifyInfoPage= productService.getVerifyInfoList(page, Constant.VERIFY_INFO_LIST_PAGE_SIZE);
        modelMap.put("verifyInfoPage", verifyInfoPage);
        return "cms/verifyInfoList";
    }
    
    @RequestMapping("/bg/info/verifyInfo{page}_{id}.html")
    public String getInfoById(@PathVariable("page") int page,@PathVariable("id") long id,ModelMap modelMap){
        QutiaoProduct product=productService.getProductById(id);
        modelMap.put("product", product);
        modelMap.put("page", page);
        return "cms/verifyInfoDetail";
    }
    
    @RequestMapping("/bg/info/verifyInfoById.do")
    public String verifyInfoById(@RequestParam("keyword") String keyword,@RequestParam("id") long id,@RequestParam("page") int page,ModelMap modelMap){
        productService.verifyDisplayById(id, keyword.split(","));
        return this.getVerifyInfoList(page, modelMap);
    }
    
    @RequestMapping("/bg/info/deleteInfoById.do")
    public String deleteInfoById(@RequestParam("id") long id,@RequestParam("page") int page,ModelMap modelMap){
        productService.deleteInfoById(id);
        return this.getVerifyInfoList(page, modelMap);
    }
    
}
