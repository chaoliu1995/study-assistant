package com.chaoliu1995.english.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chaoliu1995.english.base.BaseJunit4Test;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.util.StringUtils;
import com.chaoliu1995.english.util.security.PasswordUtils;

/** 
* @Author: ChaoLiu
* @Description: 注册和登录的测试
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月22日 上午11:15:25
*/
public class UserMapperTest extends BaseJunit4Test {
	
	@Autowired
	private UserMapper userMapper;
	
	//qwer1234
	private String str = "NDc1T2JRMFNUd3BCSTVGTE9BM0luZUYxSVc5THI0bGRnbVc0U3dBV3J5UXM1MkVSdEg2L21ab3NTNHBMbmdGcFJuUXY1Smh4UVFreWNtOEpQZUpFQ1E9PQ==";	//前端加密后传来的密码
	@Test
	public void testRegister(){
		try {
			//解密
			String decryptPwd = PasswordUtils.decryptAES(str,PasswordUtils.getCLIENT_IVV(),PasswordUtils.getCLIENT_SKEY());
			//随机生成的8位字符
			String serverSalt = StringUtils.getGenerator().generate(8);
			//后端加密
			String encryptAES = PasswordUtils.encryptAES(decryptPwd, serverSalt);
			System.out.println(encryptAES);
			User user = new User();
			user.setUsername("test");
			user.setPassword(encryptAES);
			userMapper.insert(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLogin(){
		try {
			String decryptPwd = PasswordUtils.decryptAES(str, PasswordUtils.getCLIENT_IVV(), PasswordUtils.getCLIENT_SKEY());
			//测试解密,函数中传递的是testRegister() 中插入到数据库的密码
			String serverPwd = PasswordUtils.decryptAES("K3iQpvwipAut0DHsvvVguyJrPRULS6wvwOgtqPNZd9iSzIRr8tJlYTlr2V5L 9fgYSikn2NSGxg4UG8i9g+Lx8w==");
			
			System.out.println(decryptPwd);
			System.err.println(serverPwd);
			System.out.println(decryptPwd.equals(serverPwd));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
