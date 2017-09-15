package com.chaoliu1995.english.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

public class StringUtils {
	private Random random;
	private BufferedImage image;
	private Font font;
	private int distance;
	
	private static Gson gson = new Gson();
	
	private StringUtils() {}
	
	
	/**
	 * object转JSON
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
	 /**
     * The empty String {@code ""}.
     * @since 2.0
     */
    public static final String EMPTY = "";

	
	/**
	 * 校验字符串是否为null
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || str.length() == 0 || str.equals("undefined")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 字符串是否由纯数字组成
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
	     Pattern pattern = Pattern.compile("[0-9]*");
	     return pattern.matcher(str).matches();
	}
	
	/**
	 * 校验邮箱格式
	 * @param str
	 * @return
	 */
	public static boolean checkEmail(String str){
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+");
	     return pattern.matcher(str).matches();
	}
	
	/**
	 * 生成一个code 当前毫秒值+随机数
	 * @return
	 */
	public static String getRandomCode(String str){
		Long millis = System.currentTimeMillis();
		Random rd = new Random();
		millis += rd.nextInt();
		return str + String.valueOf(millis);
	}
	
	/**
	 * 生成验证码
	 * @param str
	 * @param show
	 * @param output
	 * @return
	 * @throws IOException
	 */
	public String getCheckCodeImage(String str, int show, OutputStream output)throws IOException{
		this.random = new Random();
		this.image = new BufferedImage(63, 30, 5);
		this.font = new Font("Arial", 0, 22);
		this.distance = 15;
		Graphics d = this.image.getGraphics();
		d.setColor(Color.LIGHT_GRAY);
		d.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
		d.setColor(new Color(this.random.nextInt(100) + 100, this.random.nextInt(100) + 150, this.random.nextInt(100) + 100));
		for (int i = 0; i < 10; i++) {
			d.drawLine(this.random.nextInt(this.image.getWidth()), this.random.nextInt(this.image.getHeight()), this.random.nextInt(this.image.getWidth()), this.random.nextInt(this.image.getHeight()));
		}
		d.setColor(Color.BLACK);
		d.setFont(this.font);
		String checkCode = "";
		char tmp = '\000';
		int x = -this.distance;
		for (int i = 0; i < show; i++) {
			tmp = str.charAt(this.random.nextInt(str.length() - 1));
			checkCode = checkCode + tmp;
			x += this.distance;
			d.setColor(Color.BLACK);
			d.drawString(""+tmp, x, this.random.nextInt(this.image.getHeight() - this.font.getSize()) + this.font.getSize());
		}
	    d.dispose();
	    ImageIO.write(this.image, "jpeg", output);
	    return checkCode;
	}
}
