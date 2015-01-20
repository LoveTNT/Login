package cn.yun.usermng.service;

import cn.yun.usermng.dao.UserDAO;
import cn.yun.usermng.domain.User;

/**
 * 业务层
 * @author cuixifan
 *
 */
public class UserService {
	private UserDAO userDAO = new UserDAO();//依赖DAO层
	
	/**
	 * 注册功能
	 * @param user
	 *   注册成功，正常返回（返回void）
	 *   注册失败，抛出异常
	 */
	public void regist(User form) throws UserException {
		/*
		 * 1. 校验用户名
		 *   > 如果存在：抛出异常
		 * 2. 校验email
		 *   > 如果存在：抛出异常
		 * 3. 把form给dao，完成添加
		 */
		/*
		 *  1. 校验用户名
		 */
		// 1.1 使用form的username调用dao的findByUsername()方法，得到User对象
		User user = userDAO.findByUsername(form.getUsername());
		// 1.2 判断返回的是不是null，如果不是说明用户名已被注册，抛出异常
		if(user != null) throw new UserException("用户名已被注册");
		
		/*
		 *  2. 校验Email
		 */
		// 1.1 使用form的email调用dao的findByEmail()方法，得到User对象
		user = userDAO.findByEmail(form.getEmail());
		// 1.2 判断返回的是不是null，如果不是说明Email已被注册，抛出异常
		if(user != null) throw new UserException("Email已被注册");
		
		/*
		 * 3. 把form交给dao，让dao保存到xml中 
		 */
		userDAO.add(form);
	}
	
	/**
	 * 登录功能
	 * @param user
	 * @return
	 * 
	 * 登录的参数：用户名和密码
	 * 登录的返回值：对应数据库信息
	 * @throws UserException 
	 */
	/*
	 * 登陆功能
	 * 登陆参数：用户名和密码
	 * 返回值：对应的数据库信息
	 */
	public User login(User form) throws UserException {
		/*
		 * 1. 使用form的username去调用dao的findByUsername()方法，返回User user对象
		 * 2. 判断user是否为空
		 *   3. user == null：说明用户名不存在，抛出异常
		 *   4. user != null：使用form的password与user的password进行比较，如果不同，说明密码错误，抛出异常
		 *   5. 如果密码相同，返回user对象！
		 */
		// 使用表单中客户填写的用户名去数据库中查询User对象
		User user = userDAO.findByUsername(form.getUsername());
		// 如果在数据库中查询的结果为null，说明表单中填写的用户名不存在
		if(user == null) throw new UserException("用户名不存在！");
		// 如果在数据库查询出了user对象，那么比较user的密码（数据库中正确的密码）与form的密码（客户填写的密码）进行比较
		if(!user.getPassword().equals(form.getPassword())) {//如果密码不同，说明客户填写的密码错误
			throw new UserException("密码错误！");
		}
		// 如果密码也正确，返回user
		return user;
	}
}
