package com.qutiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qutiao.domain.QutiaoReview;
import com.qutiao.service.IReviewService;

@Controller
public class ReviewController {
	@Autowired
	IReviewService reviewService;

	@RequestMapping("/addReview.do")
	public String addReview(QutiaoReview qutiaoReview) {
		if (qutiaoReview == null || qutiaoReview.getPid() == null
				|| qutiaoReview.getPid() <= 0)
			return "redirect:index.html";
		String url = "detail_" + qutiaoReview.getPid() + "_1.html";
		if (qutiaoReview.getUid() == null || qutiaoReview.getUid() <= 0)
			return "redirect:/jsp/login.jsp";
		boolean flag = reviewService.addReview(qutiaoReview);

		return "redirect:" + url;
	}

	@RequestMapping("/agreeReview.do")
	@ResponseBody
	public String agreeProduct(long id) {
		boolean flag = reviewService.agreeReview(id);
		if (flag)
			return "{rs:1}";
		else
			return "{rs:0}";
	}

	@RequestMapping("/disagreeReview.do")
	@ResponseBody
	public String disagreeProduct(long id) {
		boolean flag = reviewService.disagreeReview(id);
		if (flag)
			return "{rs:1}";
		else
			return "{rs:0}";
	}
}
