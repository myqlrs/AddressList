package view;

import javabean.person;
import util.JDBCUtils;
import util.accessDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author 孟赟强
 * @date 2020/9/9-1:18
 */
class addlinkman extends add_mod implements ActionListener {
	private static String call;
	JButton jbAdd, jbCancel; //添加、取消按钮

	public addlinkman(String s, person p) {
		super(p);
		call = s;

		jbAdd = new JButton("添加");
		jbAdd.setFont(new Font("微软雅黑", Font.BOLD, 16));
		jbAdd.setSize(100, 30);
		jbAdd.setLocation(270, 450);
		jbAdd.addActionListener(this);
		add(jbAdd);
		jbCancel = new JButton("取消");
		jbCancel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		jbCancel.setSize(100, 30);
		jbCancel.setLocation(390, 450);
		jbCancel.addActionListener(this);
		add(jbCancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbAdd) {// 添加
			try {
				Connection c = JDBCUtils.getConnection();
				Statement sta = c.createStatement(); // 创建SQL语句对象
				String sql = "insert into linkman(ID,strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted) values(?,?,?,?,?,?,?,?,?)";
				if (getjTextFields().get(0).getText().equals("")) {
					JOptionPane.showMessageDialog(this, "姓名不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
					getjTextFields().get(0).requestFocus();
				} else if (sta.executeQuery(
						"select * from linkman where linkman.ID='" + accessDB.getnowID(call) + "' and strName='" + getjTextFields().get(0).getText() + "'")
						.next()) {
					JOptionPane.showMessageDialog(this, "姓名已存在！", "提示", JOptionPane.INFORMATION_MESSAGE);
					getjTextFields().get(0).requestFocus();
				} else {
					PreparedStatement psInsert = c.prepareStatement(sql);// 创建预编译SQL语句
					psInsert.setString(1, accessDB.getnowID(call));
					for (int i = 0; i < getjTextFields().size(); i++) {
						psInsert.setString(i + 2, getjTextFields().get(i).getText());
					}
					psInsert.executeUpdate();
					for (JTextField jtf : getjTextFields()) {//clear
						jtf.setText("");
					}
					JOptionPane.showMessageDialog(this, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				}
				JDBCUtils.close(sta, c);
			} catch (Exception e1) {
				System.err.println("异常: " + e1.getMessage());
			} // try-catch结构结束
		}
		if (e.getSource() == jbCancel) {
			this.dispose();
		}
	}
}
