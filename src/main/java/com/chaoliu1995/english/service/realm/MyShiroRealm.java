package com.chaoliu1995.english.service.realm;

import com.chaoliu1995.english.dao.UserMapper;
import com.chaoliu1995.english.dao.WechatUserMapper;
import com.chaoliu1995.english.dto.MiniProgramLoginResult;
import com.chaoliu1995.english.dto.ResultDTO;
import com.chaoliu1995.english.entity.User;
import com.chaoliu1995.english.entity.WechatUser;
import com.chaoliu1995.english.util.Consts;
import com.chaoliu1995.english.util.HttpUtils;
import com.chaoliu1995.english.util.StringUtils;
import com.chaoliu1995.english.util.security.PasswordUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/12/7 14:17
 */
public class MyShiroRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtils passwordUtils;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws AuthenticationException {
        User sysUser = (User) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());
        //微信小程序登录逻辑
        if(password.equals(Consts.MINI_PROGRAM)){
            ResultDTO<User> resultDTO = new ResultDTO<>();
            miniProgramLogin(username,resultDTO);
            if(resultDTO.getStatus().equals(Consts.ERROR)){
                throw new AccountException(resultDTO.getMessage());
            }
            this.setSession(Consts.SESSION_USER, resultDTO.getData());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
            return info;
        }
        User user = new User();
        user.setUsername(username);
        user = userMapper.selectOne(user);
        if (user == null) {
            throw new AccountException("用户不存在!");
        }
        try{
            //客户端密文AES解密
            String decryptPwd = passwordUtils.decryptAESForClient(password, passwordUtils.getCLIENT_SKEY(), passwordUtils.getCLIENT_IVV());
            //服务器密文AES解密
            String serverPwd = passwordUtils.decryptAES(user.getPassword(), passwordUtils.getSERVER_CRYPT_KEY(),passwordUtils.getSERVER_IV());
            if(!serverPwd.equals(decryptPwd)){
                throw new AccountException("用户名或密码错误!");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("密码解密错误，用户名："+username);
            throw new AccountException("用户名或密码错误!");
        }
        this.setSession(Consts.SESSION_USER, user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
        return info;
    }

    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    @Value("${appId}")
    private String appId;

    @Value("${appSecret}")
    private String appSecret;

    @Autowired
    private WechatUserMapper wechatUserMapper;

    /**
     * 小程序登录，请求微信接口
     * @param jsCode
     * @param resultDTO
     */
    private void miniProgramLogin(String jsCode, ResultDTO<User> resultDTO){
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(Consts.WECHAT_MINI_PROGRAM_LOGIN_URL).append(Consts.QUESTION_MARK)
                .append("appid=").append(appId).append("&secret=").append(appSecret)
                .append("&js_code=").append(jsCode).append("&grant_type=authorization_code");
        String jsonStr = HttpUtils.get(urlBuilder.toString());
        MiniProgramLoginResult loginResult = StringUtils.getGson().fromJson(jsonStr,MiniProgramLoginResult.class);
        if (loginResult.getErrcode() != 0){
            resultDTO.setMessage(loginResult.getErrmsg());
            return;
        }
        WechatUser wechatUser = wechatUserMapper.getByUnionId(loginResult.getUnionid());
        User user;
        if(wechatUser == null){
            user = new User();
            user.setUsername(jsCode);
            user.setPassword(UUID.randomUUID().toString());
            userMapper.insert(user);
            wechatUser = new WechatUser();
            wechatUser.setOpenId(loginResult.getOpenid());
            wechatUser.setUnionId(loginResult.getUnionid());
            wechatUser.setUserId(user.getId());
            wechatUser.setCreateTime(new Date());
            wechatUserMapper.insert(wechatUser);
        }else{
            user = userMapper.selectByPrimaryKey(wechatUser.getUserId());
        }
        resultDTO.setStatus(Consts.SUCCESS);
        resultDTO.setData(user);
    }
}
