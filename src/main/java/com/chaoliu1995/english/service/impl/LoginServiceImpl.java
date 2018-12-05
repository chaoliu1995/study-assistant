package com.chaoliu1995.english.service.impl;

import com.chaoliu1995.english.dao.UserMapper;
import com.chaoliu1995.english.dto.BaseResult;
import com.chaoliu1995.english.dto.LoginDTO;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.service.LoginService;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.security.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/** 
* @Author: ChaoLiu
* @Description: 
* @Email: chaoliu1995@qq.com
* @CreateDate: 2017年10月21日 下午7:49:29
*/
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private PasswordUtils passwordUtils;

    @Autowired
	private UserMapper userMapper;
	
	@Override
    public void login(LoginDTO loginDTO, BaseResult result) {
		logger.info("用户登录："+loginDTO.getUsername());
		User dbUser = new User();
		dbUser.setUsername(loginDTO.getUsername());
		List<User> userList = userMapper.select(dbUser);

		if(userList == null || userList.size() < 1){
			result.setMessage("账号不存在");
			return;
		}
		if(userList.size() > 1){
			logger.error("同一用户名下存在多条数据：" + loginDTO.getUsername());
			result.setMessage("用户数据异常");
			return;
		}
		dbUser = userList.get(0);
		try {
			//客户端密文AES解密
			String decryptPwd = passwordUtils.decryptAESForClient(loginDTO.getPassword(), passwordUtils.getCLIENT_SKEY(), passwordUtils.getCLIENT_IVV());
			//服务器密文AES解密
			String serverPwd = passwordUtils.decryptAES(dbUser.getPassword(),passwordUtils.getSERVER_CRYPT_KEY(),passwordUtils.getSERVER_IV());
			if(serverPwd.equals(decryptPwd)){
				result.setStatus(Consts.SUCCESS);
				return;
			}
			result.setMessage("密码错误");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			result.setMessage("程序异常！请联系管理员");
		}
	}
	
}
