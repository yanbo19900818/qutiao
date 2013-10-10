package com.qutiao.crawler.thread;

import java.util.List;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.qutiao.crawler.CrawlerUtil;
import com.qutiao.crawler.bean.InfoBean;
import com.qutiao.crawler.bean.InfoListBean;
import com.qutiao.domain.QutiaoDispatcher;
import com.qutiao.domain.QutiaoProduct;
import com.qutiao.service.IDispatcherService;
import com.qutiao.service.IProductService;
import com.qutiao.util.url.UrlConvertTool;
import com.qutiao.util.url.spread.IUrlConverter;
import com.qutiao.util.url.spread.SpreedUrlConverterTool;
import com.qutiao.util.url.spread.UrlConvertFactory;

public class CrawlerTimertask extends TimerTask {

	@Autowired
	private IProductService productService;
	@Autowired
	private IDispatcherService dispatcherService;

	@Override
	public void run() {
		List<Integer> boardIdList = CrawlerUtil.getBoardIdList();
		for (Integer boardId : boardIdList) {
			int page = 1;
			boolean hasNext = true;
			while (hasNext) {
				List<InfoListBean> infoListBeanList = CrawlerUtil.getInfoList(
						boardId, page);
				// 逐条去数据库里面检查是否存在，若全页都不存在，则page+1,若存在，则访问详情接口，入库
				for (InfoListBean infoListBean : infoListBeanList) {
					long topicId = infoListBean.getTopic_id();
					if (productService.isProductExistByTopicId(topicId)) {
						hasNext = false;
						break;
					}
					InfoBean infoBean = CrawlerUtil.getInfo(boardId,
							infoListBean.getTopic_id());
					// 转换连接

					String originalUrl = UrlConvertTool.getOriginalUrl(infoBean
							.getLink());

					String webUrl = SpreedUrlConverterTool
							.convertWebUrl(originalUrl);
					String phoneUrl = SpreedUrlConverterTool
							.convertPhoneUrl(originalUrl);
					
					QutiaoDispatcher dispatcher = new QutiaoDispatcher();
					dispatcher.setOriginalUrl(originalUrl);
					dispatcher.setPhoneUrl(phoneUrl);
					dispatcher.setWebUrl(webUrl);
					long dispatcherId = dispatcherService
							.addDispatcher(dispatcher);
					QutiaoProduct product = new QutiaoProduct(infoBean);
					product.setUrl("/goto/" + dispatcherId + ".html");
					if (StringUtils.isEmpty(product.getImage()))
						continue;
					productService.addProduct(product);
				}
				page++;
			}
		}
	}
}
