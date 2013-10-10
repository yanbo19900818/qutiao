package com.qutiao.dao.common;

import java.util.List;

public class PageCreator {
	public static Page CreatePage(List result, int now, int totalNum,
			int pageSize) {
		Page page = new Page();
		int totalPage = totalNum / pageSize;
		if (totalNum % pageSize != 0)
			totalPage = totalPage + 1;
		if (now > totalPage)
			now = totalPage;
		if(now < 1)
			now = 1;
		int next = now + 1;// 下一页
		int before = now - 1;// 上一页
		if (next >= totalPage)
			next = totalPage;
		if (before <= 1)
			before = 1;
		page.setResult(result);
		page.setBefore(before);
		page.setNext(next);
		page.setNow(now);
		page.setPageSize(pageSize);
		page.setTotalNum(totalNum);
		page.setTotalPage(totalPage);
		return page;
	}
	// public static void main(String[] args) {
	// PageCreator c = new SetPage();
	// List l = new ArrayList<User>();
	// for (int i = 0; i < 10; i++) {
	// User u = new User();
	// u.setUid(Long.parseLong(i + ""));
	// u.setUsername(i + "");
	// u.setPassword(i + "");
	// l.add(u);
	// }
	// Page p = c.CreatePage(l, 2, 5, 2);
	// System.out.print(p);
	// }
}
