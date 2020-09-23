package util;

import java.sql.*;

/**
 * @author ���Sǿ
 * @date 2020/9/9-9:47
 * ���ݿ����
 */
public class accessDB {
	private static Connection con = null; // ���ݿ���������
	private static Statement stat = null; // SQL�������
	private static ResultSet rs = null; // ��������������

	public static boolean check(String user, String pwd) {// ��½��֤
		boolean flag = false;
		try {
			con = JDBCUtils.getConnection(); // �õ����ݿ�����
			stat = con.createStatement(); // ����SQL������
			rs = stat.executeQuery("select * from user");
			rs.beforeFirst();
			while (rs.next()) {
				String myuser = rs.getString("userName");
				String mypwd = rs.getString("passWord");
				if (myuser.equals(user) && mypwd.equals(pwd)) {
					flag = true; // �û�����������ȷ
				}
			}
		} catch (Exception e) {
			flag = false; // ���쳣��¼ʧ��
		} finally {
			JDBCUtils.close(rs,stat,con); // �ر����ݿ�
		}
		return flag;
	}

	public static boolean insertUser(String sql, String user, String pwd) { // �û�ע��������ݿ�
		boolean flag;
		try {
			con = JDBCUtils.getConnection(); // ���ӵ����ݿ�
			stat = con.createStatement(); // ����������
			// Ԥ�����������
			PreparedStatement psInsert = con.prepareStatement(sql);// ����Ԥ����SQL���
			psInsert.setString(1, user);
			psInsert.setString(2, pwd);
			psInsert.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace(); // �쳣���
			flag = false;
		} finally {
			JDBCUtils.close(stat,con); // �ر����ݿ�
		}
		return flag;
	}

	public static boolean isExist(String sql) {// �ж��Ƿ���ڸü�¼
		boolean flag = false;
		try {
			con = JDBCUtils.getConnection(); // �������ݿ�
			stat = con.createStatement(); // ����������
			rs = stat.executeQuery(sql); // ��ѯ
			if (rs.next()) {
				flag = true; // ����
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		} finally {
			JDBCUtils.close(rs,stat,con);
		}
		return flag;
	}

	public static int update(String sql) {// �������ݿ�
		int res; // ����ֵ
		try {
			con = JDBCUtils.getConnection(); // ���ӵ����ݿ�
			stat = con.createStatement(); // ����������
			res = stat.executeUpdate(sql);// ����
		} catch (Exception e) {
			e.printStackTrace(); // �쳣���
			res = -1; // ����ֵΪ��һ
		} finally {
			JDBCUtils.close(stat,con); // �ر����ݿ�
		}
		return res;
	}

	public static String getnowID(String s) { // ��õ�ǰ�û����
		String nowID = null;
		try {
			con = JDBCUtils.getConnection(); // ���ӵ����ݿ�
			stat = con.createStatement(); // ����������
			rs = stat.executeQuery("select * from user where userName='" + s + "'");
			while (rs.next()) {
				nowID = rs.getString("ID");
			}
		} catch (Exception e) {
			e.printStackTrace(); // �쳣���
		} finally {
			JDBCUtils.close(rs,stat,con); // �ر����ݿ�
		}
		return nowID;
	}

	public static String getnowpassWord(String s) { // ��õ�ǰ�û�����
		String nowpassWord = null;
		try {
			con = JDBCUtils.getConnection(); // ���ӵ����ݿ�
			stat = con.createStatement(); // ����������
			rs = stat.executeQuery("select * from user where userName='" + s + "'");
			while (rs.next()) {
				nowpassWord = rs.getString("passWord");
			}
		} catch (Exception e) {
			e.printStackTrace(); // �쳣���
		} finally {
			JDBCUtils.close(rs,stat,con); // �ر����ݿ�
		}
		return nowpassWord;
	}
}
