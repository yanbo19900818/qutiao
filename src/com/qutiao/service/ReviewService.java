package com.qutiao.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.cglib.beans.BeanCopier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qutiao.dao.IReviewDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoReview;
import com.qutiao.domain.QutiaoUser;
import com.qutiao.vo.Review;

@Service
public class ReviewService implements IReviewService {
	@Autowired
	IReviewDao reviewDao;
	@Autowired
	IUserService userService;
	@Autowired
	IProductService productService;
	public static BeanCopier copy = BeanCopier.create(QutiaoReview.class,
			Review.class, false);

	@Override
	public Page searchProductReview(long pid, int pageSize, int pageNum) {
		// TODO Auto-generated method stub
		Page page = reviewDao.searchReviewByPid(pid, pageSize, pageNum);
		List<QutiaoReview> qutiaoReviews = page.getResult();
		List<Review> reviews = new ArrayList<Review>();
		for (QutiaoReview qutiaoReview : qutiaoReviews) {
			Review review = createReviewFromQutiaoReview(qutiaoReview);
			if (review == null)
				continue;
			else
				reviews.add(review);
		}
		page.setResult(reviews);
		return page;
	}

	@Override
	public boolean addReview(QutiaoReview qutiaoReview) {
		// TODO Auto-generated method stubt
		qutiaoReview.setCreateTime(new Timestamp(new Date().getTime()));
		long rid = reviewDao.saves(qutiaoReview);
		if (rid <= 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean agreeReview(long rid) {
		// TODO Auto-generated method stub
		boolean flag = reviewDao.agree(rid);
		return flag;
	}

	@Override
	public boolean disagreeReview(long rid) {
		// TODO Auto-generated method stub
		boolean flag = reviewDao.disagree(rid);
		return flag;
	}

	public Review createReviewFromQutiaoReview(QutiaoReview qutiaoReview) {
		Review review = new Review();
		copy.copy(qutiaoReview, review, null);
		QutiaoUser user = userService.searchById(qutiaoReview.getUid());
		if (user == null)
			return null;
		review.setUsername(user.getName());
		review.setImage(user.getImage());
		return review;
	}
}
