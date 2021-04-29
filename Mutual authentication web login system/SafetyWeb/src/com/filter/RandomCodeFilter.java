package com.filter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/randomCode.captcha")
public class RandomCodeFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;

		response.setContentType("image/jpeg");
		int width = 160, height = 60;

		// 建立指定宽、高和BufferedImage对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics(); // 该画笔画在image上
		Color c = g.getColor(); // 保存当前画笔的颜色，用完画笔后要回复现场
		g.fillRect(0, 0, width, height);

		char[] ch = UUID.randomUUID().toString().replaceAll("\\-", "").toCharArray(); // 根据时间随即产生的UUID字符串
		int length = ch.length; // 随即字符串的长度
		String sRand = ""; // 保存随即产生的字符串
		Random random = new Random();
		for (int i = 0; i <= 4; i++) {
			// 设置字体
			g.setFont(getRandomFont());
			// 随即生成0-9的数字或字母
			String rand = new Character(ch[random.nextInt(length)]).toString();
			sRand += rand;
			// 设置随机颜色
			g.setColor(new Color(255, 0, 0));
			g.drawString(rand, 25 * i + 15, 40);
		}
		// 产生随即干扰点
		for (int i = 0; i < 20; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			g.drawOval(x1, y1, 2, 2);
		}
		g.setColor(c); // 将画笔的颜色再设置回去
		g.dispose();

		// 将验证码记录到session
		httprequest.getSession().setAttribute("random-captcha", sRand);

		ServletOutputStream out = httpresponse.getOutputStream();
		ImageIO.write(image, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}

	// 产生随即的字体
	private Font getRandomFont() {
		Random random = new Random();
		Font font[] = new Font[5];
		font[0] = new Font("Consolas", Font.PLAIN, 42);
		return font[random.nextInt(1)];
	}
}
