package com.chaoliu1995.english.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.chaoliu1995.english.base.impl.BaseServiceImpl;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.service.LoginService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.security.PasswordUtils;

/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午7:49:29
*/
@Service("loginService")
public class LoginServiceImpl extends BaseServiceImpl<User> implements LoginService {

	@Override
	public Map<String, String> login(User user,Map<String, String> resultMap) {
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
			resultMap.put(Consts.MESSAGE, "程序异常！请联系管理员");
			return resultMap;
		}
	}
	
}
