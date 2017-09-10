package cn.com.weiwang.cms.backend.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class CheckcodeServlet extends HttpServlet {

	private int width;
	private int height;
	private int number; //��ʾ���ٸ��ַ�
	private String codes; //����Щ�ַ��ɹ�ѡ��
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		width = Integer.parseInt(config.getInitParameter("width"));
		height = Integer.parseInt(config.getInitParameter("height"));
		number = Integer.parseInt(config.getInitParameter("number"));
		codes = config.getInitParameter("codes");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("image/jpeg");
		
		
		//����һ��ͼƬ
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		
		//������ɫ����
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		//���ڱ߿�
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width-1, height-1);
		
		Random random = new Random();
		
		//ÿ���ַ�ռ�ݵĿ��
		int x = (width - 1) / number;
		int y = height -4;
		
		StringBuffer sb = new StringBuffer();
			
		//��������ַ�
		for(int i=0; i<number; i++){
			String code = String.valueOf( codes.charAt( random.nextInt(codes.length())) );
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red,green,blue));
			
			Font font = new Font("Arial",Font.PLAIN,random(height/2,height));
			g.setFont(font);
			
			g.drawString(code, i * x + 1, y);
			
			sb.append(code);
		}
		
		request.getSession().setAttribute("codes",sb.toString());
		
		//�������һЩ��
		for(int i=0; i<50; i++){
			int red = random.nextInt(255);
			int green = random.nextInt(255);
			int blue = random.nextInt(255);
			g.setColor(new Color(red,green,blue));
			g.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		OutputStream out = response.getOutputStream();
//		//��ͼƬת��ΪJPEG����
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(image);
//		  ImageIO.write(dstImage, /*"GIF"*/ formatName /* format desired */ , new File(dstName) /* target */ );
		  //ImageIO.write(dstImage, formatName, new File(dstName));
		  ImageIO.write(image,"jpeg",out); 
		  image.flush();

		out.flush();
		out.close();
		
	}
	
	/**
	 * ����һ����min��max֮��������
	 * @param min
	 * @param max
	 * @return
	 */
	private int random(int min,int max){
		int m = new Random().nextInt(999999) % (max - min);
		return m + min;
	}

}
