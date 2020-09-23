package view;

import javabean.person;
import util.accessDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 孟赟强
 * @date 2020/9/9-0:38
 */
class modifylinkman extends add_mod implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static String call;
	public static String id;
	JButton jbModify, jbCancel;

	public modifylinkman(String s, person p) {
		super(p);
		call = s;
		setTitle(s);

		jbModify = new JButton("修改");
		jbModify.setFont(new Font("微软雅黑", Font.BOLD, 16));
		jbModify.setSize(100, 30);
		jbModify.setLocation(270, 450);
		jbModify.addActionListener(this);
		add(jbModify);
		jbCancel = new JButton("取消");
		jbCancel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		jbCancel.setSize(100, 30);
		jbCancel.setLocation(390, 450);
		jbCancel.addActionListener(this);
		add(jbCancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbCancel) {
			this.dispose();
		} else if (e.getSource() == jbModify) {
			id = accessDB.getnowID(call);
			String cid = id;
			String nowName= getjTextFields().get(0).getText();

			accessDB.update("update linkman set strName='" + getjTextFields().get(0).getText() + "',strSex='" + getjTextFields().get(1).getText() + "',strPhone='"
					+ getjTextFields().get(2).getText() + "',strPhone2='" + getjTextFields().get(3).getText() + "',strQQ='" + getjTextFields().get(4).getText() + "',strpost='" + getjTextFields().get(5).getText()
					+ "',strUnit='" + getjTextFields().get(6).getText() + "',strSorted='" + getjTextFields().get(7).getText() + "' where linkman.ID='" + cid
					+ "' and strName='" + nowName + "'");
			JOptionPane.showMessageDialog(this, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
