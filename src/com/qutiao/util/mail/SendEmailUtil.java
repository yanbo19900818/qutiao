package com.qutiao.util.mail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import com.qutiao.util.PropertiesManager;

/**
 * 邮件工具类
 * 
 * @author lenovo
 * 
 */
public final class SendEmailUtil {

    private static Logger logger = Logger.getLogger("System");

    static Properties prop = null;
    static {
        prop = new Properties();
        try {
            prop.load(PropertiesManager.class.getResourceAsStream("/email.properties"));
        } catch (Exception e) {
            logger.error("read excelPath.properties error!!!", e);
        }
    }

    /**
     * 发送文本
     * 
     * @param content
     */
    public static void sendText(String content) {
        SimpleEmail email = new SimpleEmail();
        email.setTLS(true);
        email.setHostName("smtp.163.com");
        email.setAuthentication("zzj_2046", "");

        try {
            email.addTo("zhangzhanjie@mobcent.com");
            email.setFrom("zzj_2046@163.com");
            email.setSubject("激活码");
            email.setCharset("utf-8");
            email.setMsg(content);
            email.send();
        } catch (EmailException e) {
            logger.error("email send failed");
        }
    }

    /**
     * 发送网页
     * 
     * @param content
     */
    public static void sendRegister(String title, String userName, String content, String toEmail) {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您注册趣挑网,点击下面的链接完成注册。<br/><br/>");
        sb.append("<a href=\"" + content + "\">" + content + "</a><br/><br/>");
        sb.append("如无法打开页面，请将上面的链接地址拷贝并粘贴到浏览器的地址栏再访问。<br/><br/>");
        sendHtml(title, userName, sb.toString(), toEmail);

    }

    public static void sendPassword(String title, String userName, String toEmail, String content) {
        StringBuffer sb = new StringBuffer();
        sb.append("请点击下面链接，进行密码重置<br/><br/>");
        sb.append("<a href=\"" + content + "\">" + content + "</a><br/><br/>");
        sb.append("如无法打开页面，请将上面的链接地址拷贝并粘贴到浏览器的地址栏再访问。<br/><br/>");
        sendHtml(title, userName, sb.toString(), toEmail);
    }

  
    public static void sendHtml(String title, String userName, String content, String toEmail) {
        HtmlEmail email = new HtmlEmail();
        email.setSslSmtpPort(prop.getProperty("port"));
        email.setHostName(prop.getProperty("hostName"));
        email.setAuthentication(prop.getProperty("userName"), prop.getProperty("password"));
        try {
        	
            email.setSentDate(new Date());
            email.addTo(toEmail);
            email.setFrom(prop.getProperty("fromEmail"), "趣挑网");
            email.setSubject(title);
            email.setCharset("utf-8");
            StringBuffer sb = new StringBuffer();
            sb.append("尊敬的" + userName + ", 您好！<br/><br/>");

            sb.append(content);

            sb.append("趣挑网（www.qutiao.com)是为所有网购爱好者提供最高性价比，最全实惠商品的电商导购平台。<br/>");
            sb.append("官方网址： http://www.qutiao.com<br/>");

            email.setHtmlMsg(sb.toString());
            email.setSocketTimeout(30 * 1000);
            email.send();
        } catch (EmailException e) {
            logger.error("email send failed ,content=" + content + ",message is " + e.getMessage(), e);
        }
    }

    /**
     * 读取文件
     * 
     * @param file
     *            文件路径
     * @return 返回二进制数组
     */
    public static byte[] readFile(String file) {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            int bytesRead;
            byte buffer[] = new byte[1024 * 1024];
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
                Arrays.fill(buffer, (byte) 0);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
               logger.error("email send failed", e);
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    logger.error("email send failed");
                }
            }
        }
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        sendRegister("恭喜成为摩讯会员", "激活码", "wjw", "2424837471@qq.com");
    }
}

/**
 * Smtp认证
 */
class SmtpAuthenticator extends Authenticator {
    String username = null;
    String password = null;

    // SMTP身份验证
    public SmtpAuthenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.username, this.password);
    }

}

class ByteArrayDataSource implements DataSource {

    private final String contentType;
    private final byte[] buf;
    private final int len;

    public ByteArrayDataSource(byte[] buf, String contentType) {
        this(buf, buf.length, contentType);
    }

    public ByteArrayDataSource(byte[] buf, int length, String contentType) {
        this.buf = buf;
        this.len = length;
        this.contentType = contentType;
    }

    public String getContentType() {
        if (contentType == null)
            return "application/octet-stream";
        return contentType;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(buf, 0, len);
    }

    public String getName() {
        return null;
    }

    public OutputStream getOutputStream() {
        throw new UnsupportedOperationException();
    }

}
