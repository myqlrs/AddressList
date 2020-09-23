package javabean;

/**
 * @author 孟赟强
 * @date 2020/9/7-23:10
 * 一个联系人的信息包括姓名、单位、职务、电话，一个联系人可以有多个电话信息，如手机、小灵通、办公电话、住宅电话
 */
public class person {
    private String strName;  //用户名
    private String strSex;   //性别
    private String strPhone;    //Phone1
    private String strPhone2;   //Phone2
    private String strQQ;   //QQ
    private String strpost; //职务
    private String strUnit; //单位
    private String strSorted;  //分组

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrSex() {
        return strSex;
    }

    public void setStrSex(String strSex) {
        this.strSex = strSex;
    }

    public String getStrPhone() {
        return strPhone;
    }

    public void setStrPhone(String strPhone) {
        this.strPhone = strPhone;
    }

    public String getStrPhone2() {
        return strPhone2;
    }

    public void setStrPhone2(String strPhone2) {
        this.strPhone2 = strPhone2;
    }

    public String getStrQQ() {
        return strQQ;
    }

    public void setStrQQ(String strQQ) {
        this.strQQ = strQQ;
    }

    public String getStrpost() {
        return strpost;
    }

    public void setStrpost(String strpost) {
        this.strpost = strpost;
    }

    public String getStrUnit() {
        return strUnit;
    }

    public void setStrUnit(String strUnit) {
        this.strUnit = strUnit;
    }

    public String getStrSorted() {
        return strSorted;
    }

    public void setStrSorted(String strSorted) {
        this.strSorted = strSorted;
    }
}
