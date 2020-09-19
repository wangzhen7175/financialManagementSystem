package com.superprince.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.superprince.entity.User;
import com.superprince.exception.UserException;

/**
 * 用户模块业务层
 *
 * @author
 */
public interface UserService {

	User login(String userName);

    void updatePassword(String loginName, String newPass, String oldPass) throws UserException;



    void activatioin(String code) throws UserException;

	/**
	 * 校验用户名是否存在
	 * @param loginName
	 * @return
	 */
	boolean checkUserName(String loginName);

	boolean checkEmail(String email);

    void regist(User user) throws AddressException, MessagingException;

    String isStatus(Map user);

    String uid(String loginName);
}	