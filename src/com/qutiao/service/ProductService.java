package com.qutiao.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qutiao.dao.IKeywordDao;
import com.qutiao.dao.IKeywordProductRelationDao;
import com.qutiao.dao.IProductDao;
import com.qutiao.dao.common.Page;
import com.qutiao.domain.QutiaoKeyword;
import com.qutiao.domain.QutiaoProduct;
import com.qutiao.domain.QutiaoProductKeywordRelation;
import com.qutiao.util.QutiaoConstant;
import com.qutiao.vo.KeywordVO;
import com.qutiao.vo.ProductDetail;
import com.qutiao.vo.ProductVO;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductDao productDao;
	@Autowired
	private IKeywordProductRelationDao keywordProductRelationDao;
	@Autowired
	private IKeywordService keywordService;
	@Autowired
	private IReviewService reviewService;
	private static BeanCopier copy = BeanCopier.create(QutiaoProduct.class,
			ProductDetail.class, false);
	public static BeanCopier productVoCopier = BeanCopier.create(
			QutiaoProduct.class, ProductVO.class, false);

	@Override
	public Page searchByKeyword(String keyWord, int pageSize, int pageNum) {
		QutiaoKeyword qutiaoKeyword = keywordService.searchKeyword(keyWord);
		if (qutiaoKeyword == null)
			return null;
		Page page = keywordProductRelationDao.searchKeywordRelationByKid(
				qutiaoKeyword.getId(), pageSize, pageNum);
		List<QutiaoProduct> products = new ArrayList<QutiaoProduct>();
		List<QutiaoProductKeywordRelation> productKeywordRelations = page
				.getResult();
		for (QutiaoProductKeywordRelation productKeywordRelation : productKeywordRelations) {
			QutiaoProduct product = productDao
					.searchById(productKeywordRelation.getPid());
			products.add(product);
		}
		List<ProductVO> productVOs = convertProductVOs(products);
		page.setResult(productVOs);
		return page;
	}

	@Override
	public Page searchAll(int pageSize, int pageNum) {
		Page page = productDao.searchAllProduct(pageSize, pageNum);
		List<QutiaoProduct> products = page.getResult();
		List<ProductVO> productVOs = convertProductVOs(products);
		page.setResult(productVOs);
		return page;
	}

	@Override
	public ProductDetail searchDetail(long pid, int reviewPage) {
		ProductDetail detail = new ProductDetail();
		QutiaoProduct product = productDao.searchById(pid);
		if (product == null)
			return null;
		List<KeywordVO> keywordVOs = keywordService
				.searchKeywordVOsByPid(product.getId());
		Page page = reviewService.searchProductReview(pid, 5, reviewPage);
		copy.copy(product, detail, null);
		detail.setKeywords(keywordVOs);
		detail.setReviewPage(page);
		return detail;
	}

	@Override
	public boolean agreeProduct(long pid) {
		// TODO Auto-generated method stub
		return productDao.updateAgreeNum(pid);
	}

	@Override
	public boolean disagreeProduct(long pid) {
		return productDao.updateDisagreeNum(pid);
	}

	@Override
	public boolean addProduct(QutiaoProduct qutiaoProduct) {
		long id = productDao.saves(qutiaoProduct);
		if (id <= 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean isProductExistByTopicId(long topicId) {
		return productDao.isProductExistByTopicId(topicId);
	}

	@Override
	public Page getVerifyInfoList(int page, int pageSize) {
		return productDao.getVerifyInfoList(page, pageSize);
	}

	@Override
	public QutiaoProduct getProductById(long id) {
		QutiaoProduct qutiaoProduct = (QutiaoProduct) productDao.searchById(id);
		String content = qutiaoProduct.getContent();
		String image = qutiaoProduct.getImage();
		if (!StringUtils.isEmpty(content))
			qutiaoProduct.setContent(content.replace("xgsize",
					QutiaoConstant.IMAGE_SIZE_640));
		if (!StringUtils.isEmpty(image))
			qutiaoProduct.setImage(image.replace("xgsize",
					QutiaoConstant.IMAGE_SIZE_640));
		return qutiaoProduct;
	}

	@Override
	public boolean deleteInfoById(long id) {
		QutiaoProduct product = productDao.searchById(id);
		product.setStatus(QutiaoConstant.PRODUCT_STAUS_DEL);
		return productDao.saves(product) > 0;
	}

	@Override
	public boolean verifyDisplayById(long id, String[] keywords) {
		if (keywords == null || keywords.length == 0)
			return false;
		for (String keyword : keywords) {
			QutiaoKeyword qutiaoKeyword = keywordService.searchKeyword(keyword);
			if (qutiaoKeyword == null) {
				qutiaoKeyword = new QutiaoKeyword(keyword,
						QutiaoConstant.PRODUCT_STAUS_DISPLAY);
				keywordService.addKeyword(qutiaoKeyword);
			}
			QutiaoProductKeywordRelation relation = new QutiaoProductKeywordRelation(
					id, qutiaoKeyword.getId(),
					QutiaoConstant.PRODUCT_STAUS_DISPLAY);
			keywordProductRelationDao.saves(relation);
		}
		QutiaoProduct qutiaoProduct = (QutiaoProduct) productDao.searchById(id);
		qutiaoProduct.setStatus(QutiaoConstant.PRODUCT_STAUS_DISPLAY);
		return productDao.saves(qutiaoProduct) > 0;
	}

	@Override
	public Page searchProductByKeywordId(long kid, int pageNum, int pageSize) {
		Page page = keywordService.searchProductByKeywordId(kid, pageNum, pageSize);
		List<QutiaoProductKeywordRelation> relations = page.getResult();
		List<QutiaoProduct> products = new ArrayList<QutiaoProduct>();
		for (QutiaoProductKeywordRelation relation : relations) {
			if (relation == null)
				continue;
			QutiaoProduct product = productDao.searchById(relation.getPid());
			products.add(product);
		}
		List<ProductVO> productVOs = convertProductVOs(products);
		page.setResult(productVOs);
		return page;
	}

	@Override
	public List<QutiaoProduct> randomProduct(int num) {
		// TODO Auto-generated method stub
		return productDao.randomProduct(num);
	}

	private List<ProductVO> convertProductVOs(List<QutiaoProduct> products) {
		List<ProductVO> productVOs = new ArrayList<ProductVO>();
		for (QutiaoProduct product : products) {
			List<KeywordVO> keywordVOs = keywordService
					.searchKeywordVOsByPid(product.getId());
			ProductVO productVO = new ProductVO();
			productVoCopier.copy(product, productVO, null);
			productVO.setKeywords(keywordVOs);
			productVOs.add(productVO);
		}
		return productVOs;
	}
}
