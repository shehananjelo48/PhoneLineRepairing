package shehan.com.sltphonelinerepairing;

public class Tecnician {
    private String TechnicianID;
    private String Name;
    private  String Aria;
    private String CABNO;
    private String CABNO_1;
    private String CABNO_2;
    private String CABNO_3;
    private String CABNO_4;
    private String VehicalNo;
    private String TelephoneNo;
    private String UserName;
    private String Password;
    private String mImageUri;
    private String Type;
    private float rate;
    private float NOR;

    public Tecnician() {
    }

    public Tecnician(String technicianID, String name, String aria, String CABNO, String CABNO_1, String CABNO_2, String CABNO_3, String CABNO_4, String vehicalNo, String telephoneNo, String userName, String password, String mImageUri, String type, float rate, float NOR) {
        TechnicianID = technicianID;
        Name = name;
        Aria = aria;
        this.CABNO = CABNO;
        this.CABNO_1 = CABNO_1;
        this.CABNO_2 = CABNO_2;
        this.CABNO_3 = CABNO_3;
        this.CABNO_4 = CABNO_4;
        VehicalNo = vehicalNo;
        TelephoneNo = telephoneNo;
        UserName = userName;
        Password = password;
        this.mImageUri = mImageUri;
        Type = type;
        this.rate = rate;
        this.NOR = NOR;
    }

    public String getTechnicianID() {
        return TechnicianID;
    }

    public void setTechnicianID(String technicianID) {
        TechnicianID = technicianID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAria() {
        return Aria;
    }

    public void setAria(String aria) {
        Aria = aria;
    }

    public String getCABNO() {
        return CABNO;
    }

    public void setCABNO(String CABNO) {
        this.CABNO = CABNO;
    }

    public String getCABNO_1() {
        return CABNO_1;
    }

    public void setCABNO_1(String CABNO_1) {
        this.CABNO_1 = CABNO_1;
    }

    public String getCABNO_2() {
        return CABNO_2;
    }

    public void setCABNO_2(String CABNO_2) {
        this.CABNO_2 = CABNO_2;
    }

    public String getCABNO_3() {
        return CABNO_3;
    }

    public void setCABNO_3(String CABNO_3) {
        this.CABNO_3 = CABNO_3;
    }

    public String getCABNO_4() {
        return CABNO_4;
    }

    public void setCABNO_4(String CABNO_4) {
        this.CABNO_4 = CABNO_4;
    }

    public String getVehicalNo() {
        return VehicalNo;
    }

    public void setVehicalNo(String vehicalNo) {
        VehicalNo = vehicalNo;
    }

    public String getTelephoneNo() {
        return TelephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        TelephoneNo = telephoneNo;
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

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getNOR() {
        return NOR;
    }

    public void setNOR(float NOR) {
        this.NOR = NOR;
    }
}
