package util;

import java.sql.*;

/**
 * @author 孟赟强
 * @date 2020/9/9-9:47
 * 数据库操作
 */
public class accessDB {
	private static Connection con = null; // 数据库连接引用
	private static Statement stat = null; // SQL语句引用
	private static ResultSet rs = null; // 结果及对象的引用

	public static boolean check(String user, String pwd) {// 登陆验证
		boolean flag = false;
		try {
			con = JDBCUtils.getConnection(); // 得到数据库连接
			stat = con.createStatement(); // 创建SQL语句对象
			rs = stat.executeQuery("select * from user");
			rs.beforeFirst();
			while (rs.next()) {
				String myuser = rs.getString("userName");
				String mypwd = rs.getString("passWord");
				if (myuser.equals(user) && mypwd.equals(pwd)) {
					flag = true; // 用户名和密码正确
				}
			}
		} catch (Exception e) {
			flag = false; // 有异常登录失败
		} finally {
			JDBCUtils.close(rs,stat,con); // 关闭数据库
		}
		return flag;
	}

	public static boolean insertUser(String sql, String user, String pwd) { // 用户注册插入数据库
		boolean flag;
		try {
			con = JDBCUtils.getConnection(); // 连接到数据库
			stat = con.createStatement(); // 创建语句对象
			// 预编译语句引用
			PreparedStatement psInsert = con.prepareStatement(sql);// 创建预编译SQL语句
			psInsert.setString(1, user);
			psInsert.setString(2, pwd);
			psInsert.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace(); // 异常输出
			flag = false;
		} finally {
			JDBCUtils.close(stat,con); // 关闭数据库
		}
		return flag;
	}

	public static boolean isExist(String sql) {// 判断是否存在该记录
		boolean flag = false;
		try {
			con = JDBCUtils.getConnection(); // 连接数据库
			stat = con.createStatement(); // 创建语句对象
			rs = stat.executeQuery(sql); // 查询
			if (rs.next()) {
				flag = true; // 存在
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			JDBCUtils.close(rs,stat,con);
		}
		return flag;
	}

	public static int update(String sql) {// 更新数据库
		int res; // 返回值
		try {
			con = JDBCUtils.getConnection(); // 连接到数据库
			stat = con.createStatement(); // 创建语句对象
			res = stat.executeUpdate(sql);// 更新
		} catch (Exception e) {
			e.printStackTrace(); // 异常输出
			res = -1; // 返回值为负一
		} finally {
			JDBCUtils.close(stat,con); // 关闭数据库
		}
		return res;
	}

	public static String getnowID(String s) { // 获得当前用户编号
		String nowID = null;
		try {
			con = JDBCUtils.getConnection(); // 连接到数据库
			stat = con.createStatement(); // 创建语句对象
			rs = stat.executeQuery("select * from user where userName='" + s + "'");
			while (rs.next()) {
				nowID = rs.getString("ID");
			}
		} catch (Exception e) {
			e.printStackTrace(); // 异常输出
		} finally {
			JDBCUtils.close(rs,stat,con); // 关闭数据库
		}
		return nowID;
	}

	public static String getnowpassWord(String s) { // 获得当前用户密码
		String nowpassWord = null;
		try {
			con = JDBCUtils.getConnection(); // 连接到数据库
			stat = con.createStatement(); // 创建语句对象
			rs = stat.executeQuery("select * from user where userName='" + s + "'");
			while (rs.next()) {
				nowpassWord = rs.getString("passWord");
			}
		} catch (Exception e) {
			e.printStackTrace(); // 异常输出
		} finally {
			JDBCUtils.close(rs,stat,con); // 关闭数据库
		}
		return nowpassWord;
	}
}
