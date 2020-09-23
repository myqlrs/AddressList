package view;

import javabean.user;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import util.accessDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 孟赟强
 * @date 2020/9/8-9:28
 */
public class login extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JTextField jbf,jpf; //用户名、密码输入框
    private JLabel l1,l2;//用户名、密码label
    private JButton btnInt, btnSin;  //登录/注册按钮
    JPanel j;
    user u = new user();

    public login() {
        setTitle("系统登录窗体");       //设置窗体的一些格式
        setSize(400, 300);           //设置窗体的大小
        setLocationRelativeTo(null); //设置窗口居中
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体的关闭方式
        setVisible(true);//设置窗体可视化
        //接下来就可以在容器中添加组件或者设置布局管理器
        //初始化面板
        j = new JPanel();
        j.setLayout(null);
        //在面板添加按钮
        l1 = new JLabel();
        l1.setBounds(new Rectangle(80, 35, 60, 25));
        l1.setText("用户名:");
        l2 = new JLabel();
        l2.setBounds(new Rectangle(80, 95, 60, 25));
        l2.setText("密  码:");

        jbf = new JTextField();
        jbf.setBounds(new Rectangle(160, 35, 120, 25));
        jpf = new JTextField();
        jpf.setBounds(new Rectangle(160, 95, 120, 25));

        btnInt = new JButton();//事件源
        btnInt.setText("登录");
        btnInt.setSize(new Dimension(60, 25));
        btnInt.setLocation(new Point(100, 150));
        btnSin = new JButton();
        btnSin.setText("注册");
        btnSin.setSize(new Dimension(60, 25));
        btnSin.setLocation(new Point(200, 150));
        btnInt.addActionListener(this);
        btnSin.addActionListener(this);
        j.add(l1);
        j.add(l2);
        j.add(jbf);
        j.add(jpf);
        j.add(btnInt);
        j.add(btnSin);
        add(j);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnInt) {
            // 登录按钮响应事件
            u.setUserName(jbf.getText().trim());            // 获得用户名
            u.setPassWord(jpf.getText().trim());            // 获得密码
            if (u.getUserName().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
            } else if (u.getPassWord().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
            } else {
                if (accessDB.check(u.getUserName(), u.getPassWord())) {
                    JOptionPane.showMessageDialog(null, "登入成功");
                    new mainview(u.getUserName());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "对不起用户名或密码错误");
                    clear();
                }
            }
        }
        if(e.getSource() == btnSin){
            // 注册按钮响应事件
            u.setUserName(jbf.getText().trim());   // 获得用户名
            u.setPassWord(jpf.getText().trim());   // 获得密码
            String sql = "";                      // 声明sql语句
            if (u.getUserName().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
            } else if (u.getPassWord().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
            } else {
                sql = "select * from user where userName='" + u.getUserName() + "'";
                if (accessDB.isExist(sql)) {// 用户名已经存在
                    JOptionPane.showMessageDialog(null, "对不起，用户名已存在！！！");
                    clear();// 清空输入文本框
                } else {
                    sql = "insert into user(userName,passWord) values(?, ?)";
                    if (accessDB.insertUser(sql, u.getUserName(), u.getPassWord())) {// 注册成功
                        JOptionPane.showMessageDialog(null, "恭喜您！！！注册成功，请登陆");
                        clear();
                    }
                }
            }
        }
    }

    public void clear() {
        jbf.setText("");                    //清空用户名文本框
        jpf.setText("");                    //清空密码文本框
        jbf.requestFocus();                 //用户名文本框得到输入焦点
    }

    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            System.err.println("set skin fail!");
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                login user = new login();
                user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗体
            }
        });
    }

}
