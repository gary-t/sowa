package pers.tbsowa.shiro.interfaces;

/**
 * 备注：需用户实现此接口，获取用户的相应信息
 *
 * Created by DELL on 2017/8/7.
 */
public interface Authorizable {

    /**
     * 获取用户名
     * @return
     */
    String getUserName();

    /**
     * 获取加密后密码
     * @return
     */
    String getPassword();

    /**
     * 账号禁用标识
     * @return
     */
    boolean getDisable();

}
