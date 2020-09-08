package shehan.com.sltphonelinerepairing.Admin;

public class Admin {
    private String AdminID,UserName,Password,SuperCode;

    public Admin() {
    }

    public Admin(String adminID, String userName, String password, String superCode) {
        AdminID = adminID;
        UserName = userName;
        Password = password;
        SuperCode = superCode;
    }

    public String getAdminID() {
        return AdminID;
    }

    public void setAdminID(String adminID) {
        AdminID = adminID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSuperCode() {
        return SuperCode;
    }

    public void setSuperCode(String superCode) {
        SuperCode = superCode;
    }
}
