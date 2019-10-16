<%@ page autoFlush="false"
	import="java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*"%>
<%
    String ImageStr = "http://localhost:8082/images/btn/Excel_Exp.gif";
    if(ImageStr==null || ImageStr.equals("")){

        response.setContentType("text/html; charset=gb2312");    
   %>
<HTML>
	<HEAD>
		<title>图片</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
	</HEAD>
	<body>
		<form id="Form1" method="post">
			<input type="text" id="ID_Text" name=ID_Text>
			<input type=submit value="GO">
		</form>
	</body>
</HTML>

<%
        }else{
        out.clear();
        response.setContentType("image/jpeg");
      response.addHeader("pragma","NO-cache");
       response.addHeader("Cache-Control","no-cache");
        response.addDateHeader("Expries",0);
        int width=300, height=100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       Graphics g = image.getGraphics();       
       //以下填充背景颜色
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, width, height);
        String random="random";
      //以下设置前景色
        g.setColor(Color.BLACK);
       g.drawString("Http://school.cnd8.com",10,20);
       g.drawString("Author:Lion[lion-a@sohu.com]",10,40);
        g.drawLine(10,50,290,50);
        g.drawImage(image,10,70);
       g.dispose();
        ServletOutputStream outStream = response.getOutputStream();
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(outStream);
        encoder.encode(image);
        outStream.close();
        response.reset();
        
       }

%>


