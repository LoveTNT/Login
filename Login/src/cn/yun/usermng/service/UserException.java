package cn.yun.usermng.service;

/**
 * 自定义异常类
 * @author cuixifan
 * 
 * 异常类之间的区别在哪里?
 *   * 类名不同！
 * 同一个类型的两个异常对象之间的区别！
 *   * 异常信息不同
 *
 */
public class UserException extends Exception {
	public UserException() {
		super();
	}

	public UserException(String message) {
		super(message);
	}	
}
