package com.chaoliu1995.assistant.service.realm;

import com.chaoliu1995.assistant.dto.MiniProgramLoginResult;
import com.chaoliu1995.assistant.dto.ResultDTO;
import com.chaoliu1995.assistant.entity.User;
import com.chaoliu1995.assistant.entity.WechatUser;
import com.chaoliu1995.assistant.mapper.UserMapper;
import com.chaoliu1995.assistant.mapper.WechatUserMapper;
import com.chaoliu1995.assistant.util.Consts;
import com.chaoliu1995.assistant.util.HttpUtils;
import com.chaoliu1995.assistant.util.StringUtils;
import com.chaoliu1995.assistant.util.security.PasswordUtils;
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
 * @Description: 自定义登录和查询权限的逻辑
 * @Date: 2018/11/8 14:06
 */
public class MyShiroRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordUtils passwordUtils;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) throws AuthenticationException {
        User user = (com.chaoliu1995.assistant.entity.User) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER);
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
        }else if(password.equals(Consts.SCAN_CODE)){
            //扫码登录
            User scanCodeUser = userMapper.selectByPrimaryKey(Integer.valueOf(username));
            if(scanCodeUser == null){
                throw new AccountException("用户不存在");
            }
            this.setSession(Consts.SESSION_USER, scanCodeUser);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
            return info;
        }
        //账号密码登录
        User user = userMapper.selectOne(new User(username));
        if (user == null) {
            throw new AccountException("用户不存在!");
        }
        if (user.getStatus() - Consts.USER_STATUS_LOCK == 0){
            throw new AccountException("用户被锁定，禁止登录!");
        }
        if(user.getPwdErrorNum() >= Consts.PWD_ERROR_NUM_MAX_VALUE){
            throw new AccountException("密码错误次数过多，禁止登录，请联系管理员重置密码!");
        }
        String decryptPwd;
        String serverPwd;
        try{
            //客户端密文AES解密
            decryptPwd = passwordUtils.decryptAESForClient(password, passwordUtils.getCLIENT_SKEY(), passwordUtils.getCLIENT_IVV());
            //服务器密文AES解密
            serverPwd = passwordUtils.decryptAES(user.getPassword(), passwordUtils.getSERVER_CRYPT_KEY(),passwordUtils.getSERVER_IV());
        }catch (Exception e){
            e.printStackTrace();
            logger.info("密码解密错误，用户名："+username);
            throw new AccountException("密码解密错误!");
        }
        if(!serverPwd.equals(decryptPwd)){
            User updatePwdErrorNum = new User();
            updatePwdErrorNum.setId(user.getId());
            updatePwdErrorNum.setPwdErrorNum(user.getPwdErrorNum()+1);
            userMapper.updateByPrimaryKeySelective(updatePwdErrorNum);
            throw new AccountException("用户名或密码错误!");
        }
        if(user.getPwdErrorNum() > 0){
            User updatePwdErrorNum = new User();
            updatePwdErrorNum.setId(user.getId());
            updatePwdErrorNum.setPwdErrorNum(0);
            userMapper.updateByPrimaryKeySelective(updatePwdErrorNum);
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
        logger.debug(urlBuilder.toString());
        String jsonStr = HttpUtils.get(urlBuilder.toString());
        logger.debug(jsonStr);
        MiniProgramLoginResult loginResult = StringUtils.getGson().fromJson(jsonStr,MiniProgramLoginResult.class);
        if (loginResult.getErrcode() != null && loginResult.getErrcode() != 0){
            resultDTO.setMessage(loginResult.getErrmsg());
            return;
        }
        WechatUser wechatUser;
        if(StringUtils.isEmpty(loginResult.getUnionid())){
            wechatUser = wechatUserMapper.getByOpenId(loginResult.getOpenid());
        }else {
            wechatUser = wechatUserMapper.getByUnionId(loginResult.getUnionid());
        }
        User user;
        if(wechatUser == null){
            Date date = new Date();
            user = new User();
            user.setEmail(UUID.randomUUID().toString());
            user.setName(jsCode);
            user.setPassword(UUID.randomUUID().toString());
            user.setStatus(Consts.USER_STATUS_LOCK);
            user.setCreateTime(date);
            userMapper.insert(user);
            wechatUser = new WechatUser();
            wechatUser.setOpenId(loginResult.getOpenid());
            wechatUser.setUnionId(loginResult.getUnionid());
            wechatUser.setUserId(user.getId());
            wechatUser.setCreateTime(date);
            wechatUserMapper.insert(wechatUser);
        }else{
            user = userMapper.selectByPrimaryKey(wechatUser.getUserId());
        }
        resultDTO.setStatus(Consts.SUCCESS);
        resultDTO.setData(user);
    }
}