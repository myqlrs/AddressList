package view;

import javabean.person;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author 孟赟强
 * @date 2020/9/9-0:28
 */
public class add_mod extends JFrame{
    private final String[] item = { "姓名", "性别", "Phone1", "Phone2", "QQ", "职务", "单位", "分组" };
    private final int[] jLsize = {80, 30, 250, 50};//功能按钮位置大小
    private final int[] jTFsize = {200, 25, 315, 50};
    private final ArrayList<JLabel> jLabels = new ArrayList<>();
    private final ArrayList<JTextField> jTextFields = new ArrayList<>();

    public ArrayList<JTextField> getjTextFields() {
        return jTextFields;
    }

    public add_mod(person p){
        setBounds(370, 100, 800, 600);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] TFitem = {p.getStrName(), p.getStrSex(), p.getStrPhone(), p.getStrPhone2(),
                p.getStrSex(), p.getStrpost(), p.getStrUnit(), p.getStrSorted()};//获取联系人信息
        for (int i = 0; i < item.length; i++) {//添加信息栏名称
            jLabels.add(i, new JLabel(item[i]));
            jTextFields.add(i, new JTextField(TFitem[i]));
        }
        for (int i = 0; i < jLabels.size(); i++) {
            jLabels.get(i).setFont(new Font("黑体", Font.BOLD, 14));
            jLabels.get(i).setSize(jLsize[0],jLsize[1]);
            jLabels.get(i).setLocation(jLsize[2],jLsize[3]+45*i);
            add(jLabels.get(i));
            jTextFields.get(i).setFont(new Font("黑体", Font.BOLD, 14));
            jTextFields.get(i).setSize(jTFsize[0],jTFsize[1]);
            jTextFields.get(i).setLocation(jTFsize[2],jTFsize[3]+45*i);
            add(jTextFields.get(i));
        }
    }
}
