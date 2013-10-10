package com.qutiao.util.mail;
public final class SendPasswordThread extends Thread {
	
	private String title;
	private String userName;
	private String toEmail;
	private String content;


	public SendPasswordThread(String title, String userName, String toEmail,
			String content) {
		super();
		this.title = title;
		this.userName = userName;
		this.toEmail = toEmail;
		this.content = content;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getToEmail() {
		return toEmail;
	}


	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public void run() {
		SendEmailUtil.sendPassword(title, userName, toEmail, content);
	}
}
