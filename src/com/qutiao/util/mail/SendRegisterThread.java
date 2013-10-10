package com.qutiao.util.mail;

public final class SendRegisterThread extends Thread{

	private String title;
	private String userName;
	private String content;
	private String toEmail;
	
	public SendRegisterThread(String title, String userName, String content,
			String toEmail) {
		super();
		this.title = title;
		this.userName = userName;
		this.content = content;
		this.toEmail = toEmail;
	}

	@Override
	public void run() {
		SendEmailUtil.sendRegister(title, userName, content, toEmail);
	}
}
