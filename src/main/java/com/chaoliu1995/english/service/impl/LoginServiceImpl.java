package com.chaoliu1995.english.service.impl;

import com.chaoliu1995.english.base.impl.BaseServiceImpl;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.service.LoginService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.security.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;


/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午7:49:29
*/
@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl<User> implements LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Override
	public Map<String, String> login(User user,Map<String, String> resultMap) {
		logger.info("用户登录："+user.getUsername());
		try {
			User dbUser = new User();
			dbUser.setUsername(user.getUsername());
			dbUser = this.getOne(dbUser);
			if(dbUser == null){
				resultMap.put(Consts.MESSAGE, "账号不存在");
				return resultMap;
			}
			//客户端密文AES解密
			String decryptPwd = PasswordUtils.decryptAES(user.getPassword(), PasswordUtils.getCLIENT_IVV(), PasswordUtils.getCLIENT_SKEY());
			//服务器密文AES解密
			String serverPwd = PasswordUtils.decryptAES(dbUser.getPassword());
			if(serverPwd.equals(decryptPwd)){
				resultMap.put(Consts.STATUS, Consts.SUCCESS);
				return resultMap;
			}
			resultMap.put(Consts.MESSAGE, "密码错误");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			resultMap.put(Consts.MESSAGE, "程序异常！请联系管理员");
			return resultMap;
		}
	}
	
}
