package com.qutiao.crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qutiao.crawler.bean.InfoBean;
import com.qutiao.crawler.bean.InfoContentBean;
import com.qutiao.crawler.bean.InfoListBean;
import com.qutiao.util.HttpUtils;

public class CrawlerUtil {

	private static final String FORUM_KEY = "4hxMP049pOOnSJM0qa";

	private static final int PAGE_SIZE = 25;

	private static final String GET_BOARD_LIST_URL = "http://sdk.mobcent.com/infosdk/info/getBoardList.do";

	private static final String GET_INFO_LIST_URL = "http://sdk.mobcent.com/infosdk/info/getInfoList.do";

	private static final String GET_INFO_URL = "http://sdk.mobcent.com/infosdk/info/getInfo.do";

	public static List<Integer> getBoardIdList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("forumKey", FORUM_KEY);
		String infoListResult = HttpUtils.httpGzipPost(GET_BOARD_LIST_URL, map);
		JSONObject infoListJO = JSONObject.fromObject(infoListResult);
		int rs = infoListJO.optInt("rs", 0);
		if (rs == 1) {
			JSONArray listArray = infoListJO.optJSONArray("list");
			List<Integer> boardIdList = new ArrayList<Integer>();
			for (int i = 0, size = listArray.size(); i < size; i++) {
				JSONObject jo = listArray.optJSONObject(i);
				JSONArray boardListArray = jo.optJSONArray("board_list");
				for (int j = 0, length = boardListArray.size(); j < length; j++) {
					JSONObject boardJO = boardListArray.optJSONObject(j);
					boardIdList.add(boardJO.optInt("board_id"));
				}
			}
			return boardIdList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<InfoListBean> getInfoList(int boardId, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("forumKey", FORUM_KEY);
		map.put("boardId", boardId);
		map.put("page", page);
		map.put("pageSize", PAGE_SIZE);
		String infoListResult = HttpUtils.httpGzipPost(GET_INFO_LIST_URL, map);
		JSONObject infoListJO = JSONObject.fromObject(infoListResult);
		int rs = infoListJO.optInt("rs", 0);
		if (rs == 1) {
			JSONArray array = infoListJO.optJSONArray("list");
			return JSONArray.toList(array, InfoListBean.class);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static InfoBean getInfo(int boardId, long topicId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("forumKey", FORUM_KEY);
		map.put("boardId", boardId);
		map.put("topicId", topicId);
		String infoResult = HttpUtils.httpGzipPost(GET_INFO_URL, map);
		JSONObject infoListJO = JSONObject.fromObject(infoResult);
		int rs = infoListJO.optInt("rs", 0);
		if (rs == 1) {
			InfoBean infoBean = (InfoBean) JSONObject.toBean(infoListJO,
					InfoBean.class);
			infoBean.setContent(JSONArray.toList(
					infoListJO.optJSONArray("content"), InfoContentBean.class));
			return infoBean;
		}
		return null;
	}

	public static void main(String[] args) {
		CrawlerUtil.getBoardIdList();
		CrawlerUtil.getInfoList(480501, 1);
		CrawlerUtil.getInfo(0, 8925929);
	}
}
