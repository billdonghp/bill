package com.ait.mail;

 import java.net.MalformedURLException;  
 import java.net.URL;  
 import org.apache.commons.mail.EmailAttachment;  
 import org.apache.commons.mail.EmailException;  
 import org.apache.commons.mail.HtmlEmail;  
 import org.apache.commons.mail.MultiPartEmail;  
 import org.apache.commons.mail.SimpleEmail;  
 /** 
 * 用apache的Commons Email发送邮件 
 * 
 * @author www.5ipig.com/blog 
 */  
 public class JavaMail {  
   /** 
    * 用apache的Commons Email发送不含附件的邮件 
    * 
    * @return 发送结果，是否发送成功 
    */  
   public String sendSingleMail() {  
     String resultmsg = "发信失败！";  
     SimpleEmail email = new SimpleEmail();  
     email.setHostName("smtp.163.com");  
     try {  
       email.addTo("ajonjun@163.com", "ajonjun");  
       email.setFrom("ajonjun@163.com", "ajonjun");  
       // 请修改成你的邮箱账号和密码。  
       email.setAuthentication("username", "password");  
       email.setSubject("发信测试标题");  
       email.setMsg("This is a simple test of commons-email");  
       resultmsg = email.send();  
       if (resultmsg != null) {  
         resultmsg = "发送成功！";  
       }  
     } catch (EmailException e) {  
       e.printStackTrace();  
     }  
     return resultmsg;  
   }  
   /** 
    * 发送单个带附件的邮件 
    * 
    * @return 发送结果，是否发送成功 
    */  
   public String sendAttachmentsMail() {  
     String resultmsg = "发信失败！";  
     // 创建一个附件对象  
     EmailAttachment attachment = new EmailAttachment();  
     // 设置附件的地址，可以是本机地址（包括绝对地址和相对地址），  
     //也可以是internet地址，如：http://www.5ipig.com/blog/attachment.php?fid=2  
     attachment.setPath("E:\\1.jpg");  
     attachment.setDisposition(EmailAttachment.ATTACHMENT);  
     attachment.setDescription("Picture of gaylys");  
     attachment.setName("gaylys");  
     // 创建多文本邮件对象  
     MultiPartEmail email = new MultiPartEmail();  
     email.setHostName("smtp.163.com");  
     try {  
       email.addTo("ajonjun@163.com", "ajonjun");  
       email.setFrom("ajonjun@163.com", "ajonjun");  
       // 请修改成你的邮箱账号和密码。  
       email.setAuthentication("username", "password");  
       email.setSubject("带附件的邮件");  
       email.setMsg("Here is the picture you wanted");  
       // 将附件添加到邮件  
       email.attach(attachment);  
       // 发送邮件  
       resultmsg = email.send();  
       if (resultmsg != null && !"".equals(resultmsg)) {  
         resultmsg = "发送成功！";  
       }  
     } catch (EmailException e) {  
       e.printStackTrace();  
     }  
     return resultmsg;  
   }  
   /** 
    * 发送html邮件 
    * 
    * @return 发送结果，是否发送成功 
    */  
   public String sendHtmlEmail() {  
     String resultmsg = "发信失败！";  
     // 创建邮件对象  
     HtmlEmail email = new HtmlEmail();  
     email.setHostName("smtp.163.com");  
     try {  
       email.addTo("ajonjun@163.com", "ajonjun");  
       email.setFrom("ajonjun@163.com", "ajonjun");  
       // 请修改成你的邮箱账号和密码。  
       email.setAuthentication("username", "password");  
       email.setSubject("发送html邮件的测试");  
       // 嵌入附件地址  
       URL url = new URL(  
           "http://www.5ipig.com/blog/attachment.php?fid=2");  
       String cid = email.embed(url, "3D桌面");  
       // 设置编码格式，不设置会出现中文乱码。  
       email.setCharset("GB2312");  
       // 写入html代码  
       email.setHtmlMsg("3D桌面 - <img src=\"\" alt=\"\" />");  
       // 设置替代文字  
       email  
           .setTextMsg("Your email client does not support HTML messages");  
       // 发送邮件  
       resultmsg = email.send();  
       if (resultmsg != null && !"".equals(resultmsg)) {  
         resultmsg = "发送成功！";  
       }  
     } catch (EmailException e) {  
       e.printStackTrace();  
     } catch (MalformedURLException e) {  
       e.printStackTrace();  
     }  
     return resultmsg;  
   }  
   public static void main(String args[]) {  
     JavaMail mail = new JavaMail();  
     System.out.println(mail.sendHtmlEmail());  
   }  
 }  
