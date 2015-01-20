package cn.yun.usermng.dao;

import java.io.PrintWriter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import cn.yun.usermng.domain.User;

/*
 * 持久层
 */
public class UserDAO {
/*
 * 按照用户名查找
 */
	public User findByUsername(String username) {
		try {
			/*
			 * 1. 把xml加载到内存（Document对象） 2.
			 * 使用xpath对Document进行查询，得到<user>元素（Element） 3. 把Element转换成User对象！
			 */
			/*
			 * 1. 加载xml到内存（Document对象）
			 */
			// 1.1 创建解析器对象
			SAXReader reader = new SAXReader();
			// 1.2 得到xml文件，得到Document
			Document doc = reader.read("F:/users.xml");

			/*
			 * 2. 使用xpath进行查询元素 //user --> 不限深度的查询user元素！
			 * //user[@username='zhangSan'] -->
			 * 要查询的是user元素，条件是名为username的属性值为zhangSan
			 */
			Element userEle = (Element) doc
					.selectSingleNode("//user[@username='" + username + "']");

			/*
			 * 如果没有查找到指定名称的元素，那么返回null，表示没有查找到！
			 */
			if (userEle == null) {
				return null;
			}

			/*
			 * 3. 把Element转换成User对象
			 */
			// 3.1 创建User对象
			User user = new User();
			// 3.2 给user的所有属性赋值，通过Elemenet元素的属性赋值
			user.setUsername(userEle.attributeValue("username"));
			user.setPassword(userEle.attributeValue("password"));
			user.setEmail(userEle.attributeValue("email"));
			user.setRegisttime(userEle.attributeValue("registtime"));

			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

/*
 * 按照email查询用户
 */
	public User findByEmail(String email) {
		try {
			/*
			 * 1. 把xml加载到内存（Document对象） 2.
			 * 使用xpath对Document进行查询，得到<user>元素（Element） 3. 把Element转换成User对象！
			 */
			/*
			 * 1. 加载xml到内存（Document对象）
			 */
			// 1.1 创建解析器对象
			SAXReader reader = new SAXReader();
			// 1.2 得到xml文件，得到Document
			Document doc = reader.read("F:/users.xml");

			/*
			 * 2. 使用xpath进行查询元素 //user --> 不限深度的查询user元素！
			 * //user[@username='zhangSan'] -->
			 * 要查询的是user元素，条件是名为username的属性值为zhangSan
			 */
			Element userEle = (Element) doc.selectSingleNode("//user[@email='"
					+ email + "']");

			/*
			 * 如果没有查找到指定Email的元素，那么返回null，表示没有查找到！
			 */
			if (userEle == null) {
				return null;
			}

			/*
			 * 3. 把Element转换成User对象
			 */
			// 3.1 创建User对象
			User user = new User();
			// 3.2 给user的所有属性赋值，通过Elemenet元素的属性赋值
			user.setUsername(userEle.attributeValue("username"));
			user.setPassword(userEle.attributeValue("password"));
			user.setEmail(userEle.attributeValue("email"));
			user.setRegisttime(userEle.attributeValue("registtime"));

			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
/*
 * 添加用户
 */
	public void add(User form) {
		try {
			/*
			 * 1. 把xml加载到内存（Document对象） 2. 在根元素下创建<user>元素对象（Element） 3.
			 * 通过User对象来给Element的属性们赋值 4. 回写！！！
			 */
			/*
			 * 1. 加载xml到内存（Document对象）
			 */
			// 1.1 创建解析器对象
			SAXReader reader = new SAXReader();
			// 1.2 得到xml文件，得到Document
			Document doc = reader.read("F:/users.xml");

			/*
			 * 2. 在根元素下创建<user>元素 通过User form对象给元素的属性赋值
			 */
			Element userEle = doc.getRootElement().addElement("user");
			userEle.addAttribute("username", form.getUsername());
			userEle.addAttribute("password", form.getPassword());
			userEle.addAttribute("email", form.getEmail());
			userEle.addAttribute("registtime", form.getRegisttime());

			/*
			 * 3. 回写 3.1 创建格式器：OutputFormat 使用\t进行缩进 是否需要换行，true表示是！
			 * 在添加格式之前，需要把原格式去除！
			 */
			// 创建输出格式化器（缩进使用\t，有换行）
			OutputFormat format = new OutputFormat("\t", true);
			// 去除原有的格式
			format.setTrimText(true);

			/*
			 * 3.2 创建输出流 指定目标文件（输出的目的地） 指定编码
			 */
			PrintWriter pw = new PrintWriter("F:/users.xml", "UTF-8");

			/*
			 * 3.3. 使用格式化器和输出流创建XMLWriter
			 */
			XMLWriter writer = new XMLWriter(pw, format);

			/*
			 * 3.4 使用XMLWriter进行回写（把Document写到输出流中）
			 */
			writer.write(doc);
			/*
			 * 3.5 关闭
			 */
			pw.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}