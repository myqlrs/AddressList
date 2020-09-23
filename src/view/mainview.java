package view;

import javabean.person;
import util.JDBCUtils;
import util.accessDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author ???S?
 * @date 2020/9/8-10:16
 */
class mainview extends JFrame implements ActionListener {
    private static String call; //????????????????????
    public static String id;  //???id
    JMenuBar jmb;// ?????
    JCheckBox jcb1, jcb2, jcb3;// ?????
    JRadioButton jrb1, jrb2, jrb3;// ??????
    JTextField jt1, jt2, jt3, jt4, jtContactName, jtDeleteGroup, jtDeleteContact;//?????
    String[] menu = {"???", "???", "???", "???", "????", "???"};
    ArrayList<JMenu> jMenus = new ArrayList<>();// ??????????
    ArrayList<JMenuItem> jMenuItems = new ArrayList<>();// ??????????§Ò?
    JTable table; //????????????
    String[] name = {"????", "???", "Phone1", "Phone2", "QQ", "???", "??¦Ë", "????"};
    person p = new person();
    JScrollPane sp = null;// ????????
    JButton jb, jb1, jbDeleteGroup,
    jbDeleteContact, jbSearchName;// ????????
    JLabel jlgroup, jlContactName;
    int count = 0;
    String[][] a;  //????????????›¥????
    String[][] backup;
    int n = 0, j = 0;
    Container mainContainer;// ????????

    public mainview(String s) {
        mainContainer = getContentPane(); //????????????
        setTitle(s);
        call = s;
        setBounds(370, 100, 800, 600);
        setVisible(true);
        setResizable(false);
        mainContainer.setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jmb = new JMenuBar();// ?????
        for (int i = 0; i < menu.length; i++) {//???????
            jMenus.add(i, new JMenu(menu[i]));
        }
        for (int i = 0; i < jMenus.size(); i++) {//????????????
            jMenus.get(i).setFont(new Font("????", Font.BOLD, 20));
            jmb.add(jMenus.get(i));
            if (i == 0) {
                jMenuItems.add(0, new JMenuItem("??????"));// ?????
                jMenuItems.add(1, new JMenuItem("??????"));
                jMenus.get(i).add(jMenuItems.get(0));
                jMenus.get(i).addSeparator();
                jMenus.get(i).add(jMenuItems.get(1));
            } else if (i == 1) {
                jMenuItems.add(2, new JMenuItem("????????"));
                jMenus.get(i).add(jMenuItems.get(2));
            } else if (i == 2) {
                jMenuItems.add(3, new JMenuItem("???????????"));
                jMenuItems.add(4, new JMenuItem("???????"));
                jMenus.get(i).add(jMenuItems.get(3));
                jMenus.get(i).addSeparator();//???????????????¦Â
                jMenus.get(i).add(jMenuItems.get(4));
            } else if (i == 3) {
                jMenuItems.add(5, new JMenuItem("???????"));
                jMenuItems.add(6, new JMenuItem("????????"));
                jMenus.get(i).add(jMenuItems.get(5));
                jMenus.get(i).addSeparator();//???????????????¦Â
                jMenus.get(i).add(jMenuItems.get(6));
            } else if (i == 4) {
                jMenuItems.add(7, new JMenuItem("????????????????"));
                jMenus.get(i).add(jMenuItems.get(7));
            } else if (i == 5) {
                jMenuItems.add(8, new JMenuItem("???????"));
                jMenuItems.add(9, new JMenuItem("??????"));
                jMenus.get(i).add(jMenuItems.get(8));
                jMenus.get(i).addSeparator();
                jMenus.get(i).add(jMenuItems.get(9));
            }
        }
        for (JMenuItem jMenuItem : jMenuItems) {
            jMenuItem.setFont(new Font("??????", Font.PLAIN, 15));
            jMenuItem.addActionListener(this);
        }
        setJMenuBar(jmb);//?????????????
//        validate();
    }

    //????????§Õ?????????§µ??????§Ó???
    public void fun(String[][] a, ResultSet rs) throws SQLException {
        int i = 0;
        while (rs.next()) {
            for (int j = 0; j < 8; j++) {
                a[i][j] = rs.getString(j + 1);
            }
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jMenuItems.get(0)) {//??????
            mainContainer.removeAll();// ?????????????????????
            JLabel jlb = new JLabel("???????????");
            mainContainer.add(jlb);// ?????????????????
            jlb.setFont(new Font("????", Font.BOLD, 22));
            jlb.setBounds(300, 10, 200, 30);// ?????????????§³
            jcb1 = new JCheckBox("?????????");
            jcb1.setFont(new Font("??????", Font.PLAIN, 15));
            mainContainer.add(jcb1);

            jcb1.setBounds(110, 50, 200, 30);
            JLabel jl1 = new JLabel("??????????");
            jl1.setFont(new Font("??????", Font.BOLD, 16));
            mainContainer.add(jl1);
            jl1.setBounds(10, 90, 100, 30);
            jt1 = new JTextField(10);
            mainContainer.add(jt1);
            jt1.setFont(new Font("????", Font.PLAIN, 15));
            jt1.setBounds(110, 90, 100, 30);

            jcb2 = new JCheckBox("????????");
            jcb2.setFont(new Font("??????", Font.PLAIN, 15));
            mainContainer.add(jcb2);

            jcb2.setBounds(320, 50, 200, 30);
            JLabel jl2 = new JLabel("????????");
            jl2.setFont(new Font("??????", Font.BOLD, 16));
            mainContainer.add(jl2);
            jl2.setBounds(240, 90, 80, 30);
            jt2 = new JTextField(10);
            mainContainer.add(jt2);
            jt2.setFont(new Font("????", Font.PLAIN, 15));
            jt2.setBounds(320, 90, 100, 30);

            jcb3 = new JCheckBox("???????????");
            jcb3.setFont(new Font("??????", Font.PLAIN, 15));
            mainContainer.add(jcb3);

            jcb3.setBounds(530, 50, 200, 30);
            JLabel jl3 = new JLabel("Phone");
            jl3.setFont(new Font("??????", Font.BOLD, 16));
            mainContainer.add(jl3);
            jl3.setBounds(440, 90, 80, 30);
            jt3 = new JTextField(10);
            mainContainer.add(jt3);
            jt3.setFont(new Font("????", Font.PLAIN, 15));
            jt3.setBounds(530, 90, 150, 30);
            jb = new JButton("???");
            mainContainer.add(jb);
            jb.setFont(new Font("??????", Font.BOLD, 22));
            jb.addActionListener(this);
            jb.setBounds(280, 135, 180, 30);

            a = new String[20][15];
            table = new JTable(a, name);
            table.setAutoResizeMode(1);//??????????????§³
            sp = new JScrollPane(table);
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setBounds(5, 180, 780, 360);
            mainContainer.add(sp);
            mainContainer.repaint();// ????repaint()????????????????
        }

        if (e.getSource() == jb) {//??????-??????
            id = accessDB.getnowID(call);
            String cid = id;
            try {
                Connection c = JDBCUtils.getConnection();
                Statement s = c.createStatement(); // ????SQL??????
                StringBuffer str = new StringBuffer(
                        "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                + cid + "' ");
                if (jcb1.isSelected()) {
                    str.append("and strName like '%" + jt1.getText() + "%'");
                }
                if (jcb2.isSelected()) {
                    str.append("and strSorted  like '%" + jt2.getText() + "%'");
                }
                if (jcb3.isSelected()) {
                    str.append("and (strPhone like '%" + jt3.getText() + "%' or strPhone2 like '%" + jt3.getText() + "%')");
                }
                ResultSet rs = s.executeQuery(str.toString());
                while (rs.next()) {
                    count++;
                }
                String[][] a = new String[count][8];
                rs = s.executeQuery(str.toString());
                fun(a, rs);//??????????a??????

                getContentPane().remove(sp);
                table = new JTable(a, name);
                table.setAutoResizeMode(1);//??????????????§³
                sp = new JScrollPane(table);
                sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                sp.setBounds(5, 180, 780, 360);
                add(sp);
                JDBCUtils.close(rs, s, c);
            } catch (Exception e1) {
                System.err.println("??: " + e1.getMessage());
            } // try-catch??????
        }
        if (e.getSource() == jMenuItems.get(1)) {//??????
            mainContainer.removeAll();
            ButtonGroup bgp = new ButtonGroup();
            JLabel jlb = new JLabel("???????????");
            mainContainer.add(jlb);
            jlb.setFont(new Font("????", Font.BOLD, 22));
            jlb.setBounds(300, 10, 180, 30);
            jrb1 = new JRadioButton("?????????");
            jrb1.setFont(new Font("??????", Font.PLAIN, 15));
            jrb1.setBounds(110, 50, 200, 30);
            bgp.add(jrb1);
            mainContainer.add(jrb1);

            jrb2 = new JRadioButton("?????????");
            jrb2.setFont(new Font("??????", Font.PLAIN, 15));
            jrb2.setBounds(320, 50, 200, 30);
            bgp.add(jrb2);
            mainContainer.add(jrb2);

            jrb3 = new JRadioButton("??QQ????");
            jrb3.setFont(new Font("??????", Font.PLAIN, 15));
            jrb3.setBounds(530, 50, 150, 30);
            bgp.add(jrb3);
            mainContainer.add(jrb3);

            jt4 = new JTextField();
            mainContainer.add(jt4);
            jt4.setFont(new Font("????", Font.PLAIN, 15));
            jt4.setBounds(250, 110, 150, 30);

            jb1 = new JButton("????");
            jb1.setFont(new Font("????", Font.BOLD, 15));
            mainContainer.add(jb1);
            jb1.addActionListener(this);
            jb1.setBounds(420, 110, 100, 30);

            a = new String[20][15];
            table = new JTable(a, name);
            table.setAutoResizeMode(1);
            sp = new JScrollPane(table);
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setBounds(5, 180, 780, 360);
            mainContainer.add(sp);
            mainContainer.repaint();
        }
        if (e.getSource() == jb1) {//??????-??????
            id = accessDB.getnowID(call);
            String cid = id;
            try {
                Connection c = JDBCUtils.getConnection();
                Statement s = c.createStatement(); // ????SQL??????
                StringBuffer str1 = new StringBuffer(
                        "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                + cid + "' ");
                if (jrb1.isSelected()) {
                    str1.append("and strName='" + jt4.getText() + "'");
                } else if (jrb2.isSelected()) {
                    str1.append("and (strPhone='" + jt4.getText() + "' or strPhone2 = '" + jt4.getText() + "')");
                } else if (jrb3.isSelected()) {
                    str1.append("and strQQ='" + jt4.getText() + "'");
                }
                ResultSet rs = s.executeQuery(str1.toString());
                while (rs.next()) {
                    count++;
                }
                String[][] a = new String[count][15];
                rs = s.executeQuery(str1.toString());
                fun(a, rs);

                getContentPane().remove(sp);
                table = new JTable(a, name);
                table.setAutoResizeMode(1);
                sp = new JScrollPane(table);
                sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                sp.setBounds(5, 180, 780, 360);
                add(sp);
                JDBCUtils.close(rs, s, c);
            } catch (Exception e1) {
                System.err.println("??: " + e1.getMessage());
            } // try-catch??????
        }
        if (e.getSource() == jMenuItems.get(2)) {//?????????
            mainContainer.removeAll();
            new addlinkman(call, p);
            mainContainer.repaint();
        }
        if (e.getSource() == jMenuItems.get(3)) {//????????
            mainContainer.removeAll();
            jlContactName = new JLabel("????????????????????");
            jlContactName.setFont(new Font("????", Font.PLAIN, 20));
            jlContactName.setBounds(130, 30, 250, 30);
            mainContainer.add(jlContactName);
            jtContactName = new JTextField(20);
            jtContactName.setFont(new Font("????", Font.PLAIN, 18));
            jtContactName.setBounds(380, 30, 100, 30);
            mainContainer.add(jtContactName);
            jbSearchName = new JButton("????");
            jbSearchName.setFont(new Font("????", Font.PLAIN, 20));
            jbSearchName.setBounds(490, 30, 80, 30);
            jbSearchName.addActionListener(this);
            mainContainer.add(jbSearchName);
            mainContainer.repaint();
        }
        if (e.getSource() == jbSearchName) {//????????->???????
            id = accessDB.getnowID(call);
            String cid = id;
            try {
                Connection c = JDBCUtils.getConnection();
                Statement sta = c.createStatement(); // ????SQL??????
                ResultSet rs = null;
                if (jtContactName.getText().equals("") == true) {
                    JOptionPane.showMessageDialog(this, "?????????????????", "???", JOptionPane.INFORMATION_MESSAGE);
                    jtContactName.requestFocus();
                } else if (!sta.executeQuery(
                        "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                + cid + "' and strName='" + jtContactName.getText() + "'")
                        .next()) {
                    JOptionPane.showMessageDialog(this, "?????????????", "???", JOptionPane.INFORMATION_MESSAGE);
                    jtContactName.requestFocus();
                } else {
                    rs = sta.executeQuery(
                            "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                    + cid + "' and strName=='" + jtContactName.getText() + "'");
                    while (rs.next()) {
                        p.setStrName(rs.getString(1));
                        p.setStrSex(rs.getString(2));
                        p.setStrPhone(rs.getString(3));
                        p.setStrPhone2(rs.getString(4));
                        p.setStrQQ(rs.getString(5));
                        p.setStrpost(rs.getString(6));
                        p.setStrUnit(rs.getString(7));
                        p.setStrSorted(rs.getString(8));
                    }
                    new modifylinkman(call, p);
                }
                JDBCUtils.close(rs, sta, c);
            } catch (Exception e1) {
                System.err.println("??: " + e1.getMessage());
            } // try-catch??????
        }
        if (e.getSource() == jMenuItems.get(4)) {//???????
            mainContainer.removeAll();
            new password(call, mainContainer);
            mainContainer.repaint();
        }
        if (e.getSource() == jMenuItems.get(5)) {//???????
            mainContainer.removeAll();
            jlgroup = new JLabel("??????");
            jlgroup.setFont(new Font("????", Font.PLAIN, 20));
            jlgroup.setBounds(230, 50, 100, 30);
            mainContainer.add(jlgroup);
            jtDeleteGroup = new JTextField(20);
            jtDeleteGroup.setFont(new Font("????", Font.PLAIN, 18));
            jtDeleteGroup.setBounds(320, 50, 120, 30);
            mainContainer.add(jtDeleteGroup);
            jbDeleteGroup = new JButton("???");
            jbDeleteGroup.setFont(new Font("????", Font.PLAIN, 20));
            jbDeleteGroup.setBounds(450, 50, 80, 30);
            jbDeleteGroup.addActionListener(this);
            mainContainer.add(jbDeleteGroup);
            mainContainer.repaint();
        }
        if (e.getSource() == jbDeleteGroup) {//???????->??????
            id = accessDB.getnowID(call);
            String cid = id;
            try {
                Connection c = JDBCUtils.getConnection();
                Statement s = c.createStatement(); // ????SQL??????
                if (jtDeleteGroup.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "??????????????", "???", JOptionPane.INFORMATION_MESSAGE);
                } else if (!s.executeQuery(
                        "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                + cid + "' and strSorted=='" + jtDeleteGroup.getText() + "'")
                        .next()) {
                    JOptionPane.showMessageDialog(this, "???öö?????", "???", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "????????¡Â??????????????????", "???????",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        s.executeUpdate("delete from linkman where linkman.ID='" + cid + "' and strSorted='"
                                + jtDeleteGroup.getText() + "'");
                        JOptionPane.showMessageDialog(this, "??????", "???", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        jtDeleteGroup.requestFocus();
                    }
                }
                JDBCUtils.close(s, c);
            } catch (Exception e1) {
                System.err.println("??: " + e1.getMessage());
            } // try-catch??????
        }
        if (e.getSource() == jMenuItems.get(6)) {//????????
            mainContainer.removeAll();
            jlgroup = new JLabel("?????????");
            jlgroup.setFont(new Font("??????", Font.BOLD, 20));
            jlgroup.setBounds(200, 50, 120, 30);
            mainContainer.add(jlgroup);
            jtDeleteContact = new JTextField(20);
            jtDeleteContact.setFont(new Font("????", Font.PLAIN, 18));
            jtDeleteContact.setBounds(320, 50, 120, 30);
            mainContainer.add(jtDeleteContact);
            jbDeleteContact = new JButton("???");
            jbDeleteContact.setFont(new Font("????", Font.PLAIN, 20));
            jbDeleteContact.setBounds(450, 50, 80, 30);
            jbDeleteContact.addActionListener(this);
            mainContainer.add(jbDeleteContact);
            mainContainer.repaint();
        }
        if (e.getSource() == jbDeleteContact) {//????????->??????
            id = accessDB.getnowID(call);
            String cid = id;
            try {
                Connection c = JDBCUtils.getConnection();
                Statement s = c.createStatement(); // ????SQL??????
                if (jtDeleteContact.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "?????????????????", "???", JOptionPane.INFORMATION_MESSAGE);
                } else if (!s.executeQuery(
                        "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                + cid + "' and strName=='" + jtDeleteContact.getText() + "'")
                        .next()) {
                    JOptionPane.showMessageDialog(this, "???????????", "???", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int n = JOptionPane.showConfirmDialog(this, "????????????????", "???????", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        s.executeUpdate("delete from linkman where linkman.ID='" + cid + "' and strName='"
                                + jtDeleteContact.getText() + "'");
                        JOptionPane.showMessageDialog(this, "??????", "???", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        jtDeleteContact.requestFocus();
                    }
                }
                JDBCUtils.close(s, c);
            } catch (Exception e1) {
                System.err.println("??: " + e1.getMessage());
            } // try-catch??????
        }
        if (e.getSource() == jMenuItems.get(7)) {//????
            id = accessDB.getnowID(call);
            String cid = id;
            try {
                Connection c = JDBCUtils.getConnection();
                Statement sta = c.createStatement(); // ????SQL??????
                ResultSet rs = sta.executeQuery(
                        "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                + cid + "'");
                while (rs.next()) {
                    n++;
                }
                backup = new String[n][8];
                rs = sta.executeQuery(
                        "select strName,strSex,strPhone,strPhone2,strQQ,strpost,strUnit,strSorted from linkman where linkman.ID='"
                                + cid + "'");
                fun(backup, rs);
                try {
                    JFileChooser chooser2 = new JFileChooser();//???????JFileChooser?????????????
                    int state = chooser2.showSaveDialog(null);//?????????Save File???????????????
                    if (state == JFileChooser.APPROVE_OPTION) {
                        File f2 = new File(chooser2.getSelectedFile().getAbsolutePath() + ".txt");
                        FileWriter fw = new FileWriter(f2);
                        BufferedWriter fin = new BufferedWriter(fw);
                        for (j = 0; j < n; j++) {
                            for (int i = 0; i < 8; i++) {
                                fin.write(backup[j][i] + "  ");
                            }
                            fin.newLine();
                        }
                        fin.flush();
                        fin.close();
                    }
                } catch (Exception e1) {
                    System.err.println("??: " + e1.getMessage());
                } // try-catch??????
                JDBCUtils.close(rs, sta, c);
            } catch (Exception e1) {
                System.err.println("??: " + e1.getMessage());
            } // try-catch??????
        }
        if (e.getSource() == jMenuItems.get(8)) {//?????
            System.exit(0);
        }
        if (e.getSource() == jMenuItems.get(9)) {//??????
            new login();
            this.dispose();
        }
    }
}