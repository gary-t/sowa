package pers.tbsowa.shiro.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.tbsowa.shiro.interfaces.Authorizable;
import pers.tbsowa.shiro.interfaces.UserInfoManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，
 * 所以我们需要定义一系列关于URL的规则和访问权限。
 *
 */

@Configuration
public class IShiroAutoConfiguration {

	private static Log log = LogFactory.getLog(IShiroAutoConfiguration.class);

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是会报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * 
     * Filter Chain定义说明
       1、一个URL可以配置多个Filter，使用逗号分隔
       2、当设置多个过滤器时，全部验证通过，才视为通过
       3、部分过滤器可指定参数，如perms，roles
	 * @param securityManager
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		//拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public SecurityManager securityManager(Realm realm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		securityManager.setRealm(myShiroRealm(userInfoManager));
		securityManager.setRealm(realm);
		return securityManager;
	}
	
	@Bean
	public MyShiroRealm myShiroRealm(UserInfoManager userInfoManager){
		MyShiroRealm myShiroRealm = new MyShiroRealm(userInfoManager);
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());;
		return myShiroRealm;
	}

	@Bean
	@ConditionalOnMissingBean
	public UserInfoManager userInfoManager() {
		return new MockUserInfoManager();
	}

	private class MockUserInfoManager implements UserInfoManager{

		private String errorString = "AccountManager is not implemented. Please create a bean implement " + UserInfoManager.class;

		MockUserInfoManager() {
			if (log.isErrorEnabled()) {
				log.error(errorString);
			}
		}

		@Override
		public Authorizable getUserInfoByUsername(String userName) {
			throw new UnsupportedOperationException(errorString);
		}
	}
	
	@Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
       
       hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
       hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
       
       return hashedCredentialsMatcher;
    }
}
