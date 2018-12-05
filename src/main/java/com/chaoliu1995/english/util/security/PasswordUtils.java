package com.chaoliu1995.english.util.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component("passwordUtils")
public class PasswordUtils {

	@Value("${SERVER_IV}")
	@Getter
	@Setter
	private String SERVER_IV; // 16位
	// 服务器专用密钥
	@Value("${SERVER_CRYPT_KEY}")
	@Getter
	@Setter
	private String SERVER_CRYPT_KEY; // 16位

	@Value("${CLIENT_IVV}")
	@Getter
	@Setter
	private String CLIENT_IVV;

	@Value("${CLIENT_SKEY}")
	@Getter
	@Setter
	private String CLIENT_SKEY;

	public String byteArrayToHex(byte[] byteArray) {
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
	 * 返回一个8位的随机数
	 * @return
	 */
	public String s20(){
		String[] data = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String result = "";
		int r;
		for(int i=0; i<8; i++){
			r=(int)Math.floor(Math.random()*62);
			result+=data[r];
		}
		return result;
	}

	/**
	 *
	 * @param decryptString
	 * @param key
	 * @param ivv
	 * @return AES 解密
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decryptAES(String decryptString,String key,String ivv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] encrypted = Base64.getDecoder().decode(decryptString);
		byte[] original = cipher.doFinal(encrypted);
		return new String(original).substring(8);
	}

	/**
	 * 客户端解密
	 * @param decryptString
	 * @param key
	 * @param ivv
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String decryptAESForClient(String decryptString,String key,String ivv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] raw = key.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] encrypted1 = Base64.getDecoder().decode(decryptString);

		byte[] original = cipher.doFinal(Base64.getDecoder().decode(new String(encrypted1)));
		return new String(original).substring(8);
	}

	/**
	 * AES 加密
	 * @param encryptString
	 * @param sKey
	 * @param ivv
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public String encryptAES(String encryptString,String sKey,String ivv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] raw = sKey.getBytes();
		// 最后一个参数 加密的方式
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		// "算法/模式/补码方式"
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivv.getBytes());
		// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] encrypted = cipher.doFinal(encryptString.getBytes());
		// 此处使用BAES64做转码功能，同时能起到2次加密的作用。
		return Base64.getEncoder().encodeToString(encrypted);
	}

	/**
	 * MD5加密
	 * @param encryptStr
	 * @return
	 * @throws Exception
	 */
	public String encryptMd5(String encryptStr) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(encryptStr.getBytes());
		return byteArrayToHex(messageDigest.digest()).toLowerCase();
	}

	/**
	 * Sha1加密
	 * @param encryptStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public String encrySha1(String encryptStr) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
		messageDigest.update(encryptStr.getBytes());
		byte byteBuffer[] = messageDigest.digest();
		StringBuffer strHexString = new StringBuffer();
		for (int i = 0; i < byteBuffer.length; i++) {
			String hex = Integer.toHexString(0xff & byteBuffer[i]);
			if (hex.length() == 1) {
				strHexString.append('0');
			}
			strHexString.append(hex);
		}
		return strHexString.toString();
	}
}
