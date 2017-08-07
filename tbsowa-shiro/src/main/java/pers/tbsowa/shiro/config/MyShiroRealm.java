package pers.tbsowa.shiro.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import pers.tbsowa.shiro.interfaces.Authorizable;
import pers.tbsowa.shiro.interfaces.UserInfoManager;
import pers.tbsowa.shiro.utils.MD5Utils;

public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
	private UserInfoManager userInfoManager;

	/*public MyShiroRealm(UserInfoManager userInfoManager){
	    super();
		this.userInfoManager = userInfoManager;
	}*/

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

//		String pwd = MD5Utils.Encrypt(String.valueOf(token.getPassword()), token.getUsername());
        Authorizable user = userInfoManager.getUserInfoByUsername(token.getUsername());
		if(null == user){
			throw new UnknownAccountException("Account not exists.");
		}else if(user.getDisable()){
			throw new DisabledAccountException("Account has been disabled.");
		}else{
			// 记录账号最新登录时间
		}

		if(user.getPassword().equals(token.getPassword())){
//		                                                                                              盐
//    		return new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(), ByteSource.Util.bytes(token.getUsername()),getName());
            return new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(),getName());
        } else{
            throw  new IncorrectCredentialsException("Password incorrect.");
        }

	}

}
