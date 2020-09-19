package com.superprince.mapper;

import com.superprince.entity.User;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.Map;



/**
 * 用户模块持久层
 * @author 
 *
 */
public interface UserMapper{

	User getUserByName(String userName);
	
	 String status(Map user);
	
	/**
	 * 按uid和password查询
	 * @param uid
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	 int findByUidAndPassword(@Param(value = "loginname") String uid, @Param(value = "password") String password) ;
	
	
	/**
	 * 修改密码
	 * @param uid
	 * @param password
	 * @throws SQLException
	 */
	 void updatePassword(@Param(value = "loginname") String uid, @Param(value = "password") String password);
		
	/**
	 * 按用户名和密码查询
	 * @return
	 * @throws SQLException 
	 */
	 int findByLoginnameAndLoginpass(Map user) ;
		
	/**
	 * 通过激活码查询用户
	 * @param code
	 * @return
	 * @throws SQLException 
	 */
	 User  findByCode(String code) ;
		
	/**
	 * 修改用户状态
	 * @throws SQLException 
	 */
	 void updateStatus(String code) ;

	/**
	 * 校验用户名是否注册
	 * @param loginname
	 * @return
	 * @throws SQLException 
	 */
	 int ajaxValidateLoginname(String loginname) ;

	
	/**
	 * 校验Email是否注册
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	 int ajaxValidateEmail(String email) ;
		
	
	/**
	 * 添加用户
	 * @param user
	 * @throws SQLException 
	 */
	 void add(User user);
		
	/**
	 * 通过用户名查询uid
	 */
	 String findUid(String loginname);
}
