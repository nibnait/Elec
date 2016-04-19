<%@ page language="java"  pageEncoding="UTF-8"  %>
<%@ page contentType="image/jpeg"  import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*" %>
<%!
        Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}%>
<%
	//设置页面不缓存
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	//生成随机验证码
	Random random = new Random();
	String sRand = "";
	for (int i = 0; i < 4; i++) {
		String rand = String.valueOf(random.nextInt(10));
		sRand += rand;
	}
	// 将认证码存入SESSION
	session.setAttribute("CHECK_NUMBER_KEY", sRand);

	// 在内存中创建图象
	int width = 55;
	int height = 20;
	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);

	// 获取图形上下文
	Graphics g = image.getGraphics();

	// 设定背景色
	g.setColor(getRandColor(200, 250));
	g.fillRect(0, 0, width, height);

	//设定字体
	g.setFont(new Font("serif", Font.CENTER_BASELINE, 16));

	//画边框
	//g.setColor(new Color(1));
	//g.drawRect(0,0,width-1,height-1);

	// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
	g.setColor(getRandColor(160, 200));
	for (int i = 0; i < 100; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int xl = random.nextInt(12);
		int yl = random.nextInt(12);
		g.drawLine(x, y, x + xl, y + yl);
	}

	for (int i = 0; i < 4; i++) {
		String rand = sRand.substring(i, i + 1);
		// 将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110), 20 + random
				.nextInt(110), 20 + random.nextInt(110)));
		//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(rand, 13 * i + 6, 16);
	}

	// 图象生效
	g.dispose();

	// 输出图象到页面
	try {
		ImageIO.write(image, "JPEG", response.getOutputStream());
	} catch (Exception e) {
	}
	out.clear();
	out = pageContext.pushBody();
%>
