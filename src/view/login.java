package view;

import javabean.user;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import util.accessDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ���Sǿ
 * @date 2020/9/8-9:28
 */
public class login extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JTextField jbf,jpf; //�û��������������
    private JLabel l1,l2;//�û���������label
    private JButton btnInt, btnSin;  //��¼/ע�ᰴť
    JPanel j;
    user u = new user();

    public login() {
        setTitle("ϵͳ��¼����");       //���ô����һЩ��ʽ
        setSize(400, 300);           //���ô���Ĵ�С
        setLocationRelativeTo(null); //���ô��ھ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ô���Ĺرշ�ʽ
        setVisible(true);//���ô�����ӻ�
        //�������Ϳ������������������������ò��ֹ�����
        //��ʼ�����
        j = new JPanel();
        j.setLayout(null);
        //�������Ӱ�ť
        l1 = new JLabel();
        l1.setBounds(new Rectangle(80, 35, 60, 25));
        l1.setText("�û���:");
        l2 = new JLabel();
        l2.setBounds(new Rectangle(80, 95, 60, 25));
        l2.setText("��  ��:");

        jbf = new JTextField();
        jbf.setBounds(new Rectangle(160, 35, 120, 25));
        jpf = new JTextField();
        jpf.setBounds(new Rectangle(160, 95, 120, 25));

        btnInt = new JButton();//�¼�Դ
        btnInt.setText("��¼");
        btnInt.setSize(new Dimension(60, 25));
        btnInt.setLocation(new Point(100, 150));
        btnSin = new JButton();
        btnSin.setText("ע��");
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
            // ��¼��ť��Ӧ�¼�
            u.setUserName(jbf.getText().trim());            // ����û���
            u.setPassWord(jpf.getText().trim());            // �������
            if (u.getUserName().equals("")) {
                JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
            } else if (u.getPassWord().equals("")) {
                JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
            } else {
                if (accessDB.check(u.getUserName(), u.getPassWord())) {
                    JOptionPane.showMessageDialog(null, "����ɹ�");
                    new mainview(u.getUserName());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "�Բ����û������������");
                    clear();
                }
            }
        }
        if(e.getSource() == btnSin){
            // ע�ᰴť��Ӧ�¼�
            u.setUserName(jbf.getText().trim());   // ����û���
            u.setPassWord(jpf.getText().trim());   // �������
            String sql = "";                      // ����sql���
            if (u.getUserName().equals("")) {
                JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
            } else if (u.getPassWord().equals("")) {
                JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
            } else {
                sql = "select * from user where userName='" + u.getUserName() + "'";
                if (accessDB.isExist(sql)) {// �û����Ѿ�����
                    JOptionPane.showMessageDialog(null, "�Բ����û����Ѵ��ڣ�����");
                    clear();// ��������ı���
                } else {
                    sql = "insert into user(userName,passWord) values(?, ?)";
                    if (accessDB.insertUser(sql, u.getUserName(), u.getPassWord())) {// ע��ɹ�
                        JOptionPane.showMessageDialog(null, "��ϲ��������ע��ɹ������½");
                        clear();
                    }
                }
            }
        }
    }

    public void clear() {
        jbf.setText("");                    //����û����ı���
        jpf.setText("");                    //��������ı���
        jbf.requestFocus();                 //�û����ı���õ����뽹��
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
                user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رմ���
            }
        });
    }

}
