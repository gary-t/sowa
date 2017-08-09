package pers.tbsowa.common.utils;

import java.util.regex.Pattern;

public class RegexUtils {
	/**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1[34578]\\d{9}$";
    
    /**
     * 正则表达式：固定电话
     */
    public static final String REGEX_TELE = "^(0[0-9]{2,3})?([2-9][0-9]{6,7})+(/-[0-9]{1,4})?$";
 
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    
    /**
     * 正则表达式：身份证
     */
    public static final String REGEX_IDCARDNO_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    public static final String REGEX_IDCARDNO_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    
    /**
     * 正则表达式：登录名
     */
    public static final String REGEX_LOGINNAME = "^([a-z]|[0-9]|_){5,25}$";
    
    /**
     * 正则表达式：登录密码
     */
    public static final String REGEX_MIMA = "(?!^(\\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\\w~!@#$%\\^&*?]{8,16}$";
    
    /**
     * 正则表达式：姓名
     */
    public static final String REGEX_NAME = "^[\u4e00-\u9fa5]{2,20}$";
	
	/**
	 * 正则匹配字符串
	 * @param str 字符串源
	 * @param regEx 匹配规则
	 * @return
	 */
	public static boolean regexString(String str,String regEx){
      boolean result =
    		  Pattern.compile(regEx).matcher(str).find();
      return result;
	}
	
	/**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
    
    /**
     * 校验固定电话
     * @param tele
     * @return
     */
    public static boolean isTele(String tele) {
    	return Pattern.matches(REGEX_TELE, tele);
    }
 
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
    
    /**
     * 身份证校验
     * @param idcardNo
     * @return
     */
    public static boolean isIDCRADNO(String idcardNo) {
        return Pattern.matches(REGEX_IDCARDNO_15, idcardNo) || Pattern.matches(REGEX_IDCARDNO_18, idcardNo);
    }
    
    /**
     * 登录名校验
     * @param loginName
     * @return
     */
    public static boolean isLoginName(String loginName) {
    	return Pattern.matches(REGEX_LOGINNAME, loginName);
    }
    
    /**
     * 登录密码校验
     * @param passWord
     * @return
     */
    public static boolean isPassWord(String passWord) {
    	return Pattern.matches(REGEX_MIMA, passWord);
    }
    
    /**
     * 姓名校验
     * @param name
     * @return
     */
    public static boolean isName(String name) {
    	return Pattern.matches(REGEX_NAME, name);
    }
    
}
