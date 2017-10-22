package com.chaoliu1995.english.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.Getter;

public class PasswordUtils {
	private PasswordUtils (){}
	private static final String SERVER_IV = "abcd123e456abdc9"; // 16位
	// 服务器专用密钥
	private static final String SERVER_CRYPT_KEY = "8ig*lpk$=Fang5@P"; // 16位
	
	@Getter
	private static final String CLIENT_IVV = "0102030405060708";
	@Getter
	private static final String CLIENT_SKEY = "1234567891234567";

	/**
	 * 模拟客户端密码加密
	 * @param encryptStr
	 * @return 返回加密之后的字符串
	 * @throws NoSuchAlgorithmException
	 */
	public static String encryptMd5Hash(String encryptStr) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		// 输入的字符串转换成字节数组
		byte[] inputByteArray = encryptStr.getBytes();
		// inputByteArray是输入字符串转换得到的字节数组
		messageDigest.update(inputByteArray);
		// 转换并返回结果，也是字节数组，包含16个元素
		byte[] resultByteArray = messageDigest.digest();
		// 字符数组转换成字符串返回 .

		String pw = byteArrayToHex(resultByteArray);

		// 创建加密对象 并傳入加密類型
		@SuppressWarnings("unused")
		MessageDigest messageDigest1 = MessageDigest.getInstance("SHA-512");

		// 传入要加密的字符串
		messageDigest.update(pw.getBytes());
		// 得到 byte 類型结果
		byte byteBuffer[] = messageDigest.digest();

		// 將 byte 轉換爲 string
		StringBuffer strHexString = new StringBuffer();
		// 遍歷 byte buffer
		for (int i = 0; i < byteBuffer.length; i++) {
			String hex = Integer.toHexString(0xff & byteBuffer[i]);
			if (hex.length() == 1) {
				strHexString.append('0');
			}
			strHexString.append(hex);
		}
		// 得到返回結果
		return strHexString.toString();
	}

	public static String byteArrayToHex(byte[] byteArray) {
		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

	/**
	 * 服务器加密方法
	 * 
	 * @param encryptString 需加密的明文
	 * @param serverSalt 盐值 :随机8位字符
	 * @return 返回密文
	 * @throws Exception
	 */
	public static String encryptAES(String encryptString, String serverSalt) throws Exception {
		byte[] raw = SERVER_CRYPT_KEY.getBytes();
		// 最后一个参数 加密的方式
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		// "算法/模式/补码方式"
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		IvParameterSpec iv1 = new IvParameterSpec(SERVER_IV.getBytes());
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv1);
		byte[] encrypted = cipher.doFinal((serverSalt + encryptString).getBytes());

		// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
		return Base64s.encode(encrypted);
	}

	/**
	 * 服务器解密方法
	 * @param decryptString 需解密的密文
	 * @return 返回明文
	 * @throws Exception
	 */
	public static String decryptAES(String decryptString) {
		try {
			byte[] raw = SERVER_CRYPT_KEY.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			IvParameterSpec iv1 = new IvParameterSpec(SERVER_IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv1);

			byte[] encrypted1 = Base64s.decode(decryptString);
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString.substring(8);
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		}
		catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	/**
	 * 客户端密文解密方法
	 * @param decryptString 客户端传入的密文
	 * @param ivv 和客户端一致的iv
	 * @param sKey 和客户端一致密钥
	 * @return 返回明文
	 * @throws Exception
	 */
	public static String decryptAES(String decryptString, String ivv, String sKey) throws Exception {
		try {
			byte[] raw = sKey.getBytes();
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

			IvParameterSpec iv = new IvParameterSpec(ivv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

			// decryptString接收的是前端的加密之后的密码，已经是base64格式的了，见前端注释
			byte[] encrypted1 = Base64s.decode(decryptString);
			try {
				// cipher.doFinal(encrypted1)即可，如果前端对AES的结果又进行了二次base.encode操作，那么这里需要用Base64.decode解两次
				byte[] original = cipher.doFinal(Base64s.decode(new String(encrypted1)));
				String originalString = new String(original);
				return originalString.substring(8);
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
}
