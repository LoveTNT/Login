package cn.yun.usermng.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.yun.usermng.domain.User;
import cn.yun.usermng.service.UserException;
import cn.yun.usermng.service.UserService;

/**
 * 注册功能Web层
 * @author cuixifan
 *
 */
public class RegistServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		
		/*
		 * 1. 封装表单数据到User form对象中
		 * 2. 调用userService的regist(form)
		 *   3. 正常：保存成功信息到request域，转发到msg.jsp
		 *   4. 异常：保存异常信息到request域（保存form到request域（为了回显）），转发到regist.jsp
		 */
		/*
		 * 1. 封装表单数据到User form对象中
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		/********************表单的参数校验********************/
		
		/*
		 * 1. 验证码的校验
		 *   使用form中的verifyCode与session中的session_vcode进行比较
		 *     > 如果不同：保存错误信息到request中，转发到regist.jsp（为了回显，保存form到request中）
		 */
		
		
		/*
		 * 对表单的数据进行校验，其实就是form对象的校验！
		 * 
		 * 字段名称       错误信息
		 * username 用户名不能为空
		 * password 密码长度必须在2~10之间
		 * email    错误的email格式
		 * 
		 * 我们创建一个Map用来存储所有的错误信息！
		 * 每个表单项进行校验，如果有错误，使用表单项名称为key，错误信息为value
		 * 等到每个项都校验完成之后，判断map的长度即可知道是否存在错误信息，如果长度大于0，就有错误，
		 * 那么就保存map到request域中，转发到regist.jsp，如果等于0，说明没有错误，向下执行
		 */
		Map<String,String> errors = new HashMap<String,String>();
		// 对username进行校验
		String username = form.getUsername();
		if(username == null || username.trim().isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length() < 2 || username.length() > 10) {
			errors.put("username", "用户名长度必须在2 ~ 10之间！");
		}
		
		// 对password进行校验
		String password = form.getPassword();
		if(password == null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if(password.length() < 2 || password.length() > 10) {
			errors.put("password", "密码长度必须在2 ~ 10之间！");
		}

		// 对repassword进行校验
		String repassword = form.getRepassword();
		if(repassword == null || repassword.trim().isEmpty()) {
			errors.put("repassword", "确认密码不能为空！");
		} else if(!repassword.equals(password)) {
			errors.put("repassword", "两次密码输入不一致！");
		}
		
		// 对email进行校验
		String email= form.getEmail();
		if(email == null || email.trim().isEmpty()) {
			errors.put("email", "Email不能为空！");
		} else if(!email.contains("@")) {
			errors.put("email", "错误的Email格式！");
		}
		
		// 对verifyCode进行校验
		// 获取session中的验证码
		String vcode = (String)request.getSession().getAttribute("session_vcode");
		
		String verifyCode= form.getVerifyCode();
		if(verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if(!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误！");
		}
		
		/*
		 * 判断校验是否通过
		 *   查看errors的长度！如果长度>0，说明有错误，否则没错误！
		 */
		if(!errors.isEmpty()) {
			/*
			 * 保存errors到request域中
			 * 保存form对象到request域中（为了回显）
			 * 转发到regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);
			
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			
			return;
		}
		
		
		/****************************************/
		
		
		/*
		 * 2. 调用service的方法完成具体业务
		 */
		try {
			userService.regist(form);
			/*
			 * 3. 说明执行成功
			 *   保存成功信息到request域，转发到msg.jsp
			 */
			request.setAttribute("msg", "恭喜，注册成功！");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
		} catch (UserException e) {
			/*
			 * 4. 说明执行失败了
			 *   保存异常信息到request域（保存form到request域（为了回显）），转发到regist.jsp
			 */
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
		}
	}
}
