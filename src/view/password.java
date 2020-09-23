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
 * @author 孟赟强
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
        jluser = new JLabel("用户名");
        jluser.setFont(new Font("宋体", Font.BOLD, 20));
        jluser.setBounds(250, 30, 80, 30);
        mainContainer.add(jluser);
        jtuser = new JTextField(20);
        jtuser.setText(s);
        jtuser.setFont(new Font("宋体", Font.PLAIN, 18));
        jtuser.setEditable(false);//文本框不可编辑
        jtuser.setBounds(350, 30, 150, 30);
        mainContainer.add(jtuser);

        jloldPassword = new JLabel("旧密码");
        jloldPassword.setFont(new Font("宋体", Font.BOLD, 20));
        jloldPassword.setBounds(250, 80, 80, 30);
        mainContainer.add(jloldPassword);
        jpoldPassword = new JPasswordField(20);
        jpoldPassword.setFont(new Font("宋体", Font.PLAIN, 18));
        jpoldPassword.setBounds(350, 80, 150, 30);
        mainContainer.add(jpoldPassword);

        jlnewPassword = new JLabel("新密码");
        jlnewPassword.setFont(new Font("宋体", Font.BOLD, 20));
        jlnewPassword.setBounds(250, 130, 80, 30);
        mainContainer.add(jlnewPassword);
        jpnewPassword = new JPasswordField(20);
        jpnewPassword.setFont(new Font("宋体", Font.PLAIN, 18));
        jpnewPassword.setBounds(350, 130, 150, 30);
        mainContainer.add(jpnewPassword);

        jlconfirmPassword = new JLabel("确认密码");
        jlconfirmPassword.setFont(new Font("宋体", Font.BOLD, 20));
        jlconfirmPassword.setBounds(250, 180, 100, 30);
        mainContainer.add(jlconfirmPassword);
        jpconfirmPassword = new JPasswordField(20);
        jpconfirmPassword.setFont(new Font("宋体", Font.PLAIN, 18));
        jpconfirmPassword.setBounds(350, 180, 150, 30);
        mainContainer.add(jpconfirmPassword);

        jbsubmit = new JButton("提交");
        jbsubmit.setFont(new Font("宋体", Font.BOLD, 20));
        jbsubmit.setBounds(280, 240, 80, 30);
        jbsubmit.addActionListener(this);
        mainContainer.add(jbsubmit);
        jbnull = new JButton("重置");
        jbnull.setFont(new Font("宋体", Font.BOLD, 20));
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
                Statement s = c.createStatement(); // 创建SQL语句对象
                if (!new String(jpoldPassword.getPassword()).equals(nowpassWord)) {
                    JOptionPane.showMessageDialog(this, "输入要修改的旧密码错误，修改失败！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    jpnewPassword.requestFocus();
                } else if (new String(jpnewPassword.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(this, "新密码不能为空！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    jpnewPassword.requestFocus();
                } else if (!new String(jpnewPassword.getPassword())
                        .equals(new String(jpconfirmPassword.getPassword()))) {
                    JOptionPane.showMessageDialog(this, "新密码和确认密码不一致！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    jpnewPassword.requestFocus();
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "确定要修改用户密码吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        s.executeUpdate("update user set passWord='" + new String(jpconfirmPassword.getPassword())
                                + "' where userName='" + cName + "'");
                        JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                JDBCUtils.close(s, c);
            } catch (Exception e1) {
                System.err.println("异常: " + e1.getMessage());
            } // try-catch结构结束
        }
        if (e.getSource() == jbnull) {
            jpoldPassword.setText("");
            jpnewPassword.setText("");
            jpconfirmPassword.setText("");
        }
    }
}
