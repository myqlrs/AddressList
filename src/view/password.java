package view;

import util.JDBCUtils;
import util.accessDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author ���Sǿ
 * @date 2020/9/10-8:33
 */
public class password extends JFrame implements ActionListener {
    private static String call;
    JLabel jluser,jloldPassword,jlnewPassword,jlconfirmPassword;
    JTextField jtuser;
    JPasswordField jpoldPassword, jpnewPassword, jpconfirmPassword;
    JButton jbsubmit,jbnull;

    public password(String s,Container mainContainer){
        call = s;
        jluser = new JLabel("�û���");
        jluser.setFont(new Font("����", Font.BOLD, 20));
        jluser.setBounds(250, 30, 80, 30);
        mainContainer.add(jluser);
        jtuser = new JTextField(20);
        jtuser.setText(s);
        jtuser.setFont(new Font("����", Font.PLAIN, 18));
        jtuser.setEditable(false);//�ı��򲻿ɱ༭
        jtuser.setBounds(350, 30, 150, 30);
        mainContainer.add(jtuser);

        jloldPassword = new JLabel("������");
        jloldPassword.setFont(new Font("����", Font.BOLD, 20));
        jloldPassword.setBounds(250, 80, 80, 30);
        mainContainer.add(jloldPassword);
        jpoldPassword = new JPasswordField(20);
        jpoldPassword.setFont(new Font("����", Font.PLAIN, 18));
        jpoldPassword.setBounds(350, 80, 150, 30);
        mainContainer.add(jpoldPassword);

        jlnewPassword = new JLabel("������");
        jlnewPassword.setFont(new Font("����", Font.BOLD, 20));
        jlnewPassword.setBounds(250, 130, 80, 30);
        mainContainer.add(jlnewPassword);
        jpnewPassword = new JPasswordField(20);
        jpnewPassword.setFont(new Font("����", Font.PLAIN, 18));
        jpnewPassword.setBounds(350, 130, 150, 30);
        mainContainer.add(jpnewPassword);

        jlconfirmPassword = new JLabel("ȷ������");
        jlconfirmPassword.setFont(new Font("����", Font.BOLD, 20));
        jlconfirmPassword.setBounds(250, 180, 100, 30);
        mainContainer.add(jlconfirmPassword);
        jpconfirmPassword = new JPasswordField(20);
        jpconfirmPassword.setFont(new Font("����", Font.PLAIN, 18));
        jpconfirmPassword.setBounds(350, 180, 150, 30);
        mainContainer.add(jpconfirmPassword);

        jbsubmit = new JButton("�ύ");
        jbsubmit.setFont(new Font("����", Font.BOLD, 20));
        jbsubmit.setBounds(280, 240, 80, 30);
        jbsubmit.addActionListener(this);
        mainContainer.add(jbsubmit);
        jbnull = new JButton("����");
        jbnull.setFont(new Font("����", Font.BOLD, 20));
        jbnull.setBounds(390, 240, 80, 30);
        jbnull.addActionListener(this);
        mainContainer.add(jbnull);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbsubmit) {
            String nowpassWord = accessDB.getnowpassWord(call);
            String cName = call;
            try {
                Connection c = JDBCUtils.getConnection();
                Statement s = c.createStatement(); // ����SQL������
                if (!new String(jpoldPassword.getPassword()).equals(nowpassWord)) {
                    JOptionPane.showMessageDialog(this, "����Ҫ�޸ĵľ���������޸�ʧ�ܣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    jpnewPassword.requestFocus();
                } else if (new String(jpnewPassword.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(this, "�����벻��Ϊ�գ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    jpnewPassword.requestFocus();
                } else if (!new String(jpnewPassword.getPassword())
                        .equals(new String(jpconfirmPassword.getPassword()))) {
                    JOptionPane.showMessageDialog(this, "�������ȷ�����벻һ�£�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    jpnewPassword.requestFocus();
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "ȷ��Ҫ�޸��û�������", "ȷ�϶Ի���", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        s.executeUpdate("update user set passWord='" + new String(jpconfirmPassword.getPassword())
                                + "' where userName='" + cName + "'");
                        JOptionPane.showMessageDialog(this, "�޸ĳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                JDBCUtils.close(s, c);
            } catch (Exception e1) {
                System.err.println("�쳣: " + e1.getMessage());
            } // try-catch�ṹ����
        }
        if (e.getSource() == jbnull) {
            jpoldPassword.setText("");
            jpnewPassword.setText("");
            jpconfirmPassword.setText("");
        }
    }
}
