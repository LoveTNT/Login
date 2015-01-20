package cn.yun.usermng.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.yun.usermng.domain.User;
import cn.yun.usermng.service.UserException;
import cn.yun.usermng.service.UserService;

/**
 * 登录功能
 * 
 *
 */
public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		
		/*
		 * 1. 封装表单数据到User form对象中
		 * 2. 调用service的login(form)方法
		 *   * 如果正常：得到返回的User对象，保存到session中，重定向到index.jsp
		 *   * 如果异常：保存错误信息到request域，保存form（为了回显），转发到login.jsp
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		try {
			User user = userService.login(form);//调用service方法得到返回的User对象
			/*
			 * 成功了：保存user到session中，重定向到index.jsp
			 */
			request.getSession().setAttribute("session_user", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		} catch (UserException e) {
			/*
			 * 失败了：保存异常信息到request域，保存form（为了回显）
			 * 转发到login.jsp
			 */
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}
