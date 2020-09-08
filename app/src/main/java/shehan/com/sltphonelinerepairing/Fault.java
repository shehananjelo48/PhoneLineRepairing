package shehan.com.sltphonelinerepairing;

public class Fault {
    private String Telephone_No, TechnicianIDV, TechnicianIDD, LastCab, FaultType, JobNo, FaultConnectionType,SubmitDate,SubmitTime,PassTime,PassDate,Comment,CabinetNo;
    private boolean TeleFault, ADSLFault, UGFault;
    private float RateV,RateD;
    public Fault() {
    }

    public Fault(String telephone_No, String technicianIDV, String technicianIDD, String lastCab, String faultType, String jobNo, String faultConnectionType, String submitDate, String submitTime, String passTime, String passDate, String comment, String cabinetNo, boolean teleFault, boolean ADSLFault, boolean UGFault, float rateV, float rateD) {
        Telephone_No = telephone_No;
        TechnicianIDV = technicianIDV;
        TechnicianIDD = technicianIDD;
        LastCab = lastCab;
        FaultType = faultType;
        JobNo = jobNo;
        FaultConnectionType = faultConnectionType;
        SubmitDate = submitDate;
        SubmitTime = submitTime;
        PassTime = passTime;
        PassDate = passDate;
        Comment = comment;
        CabinetNo = cabinetNo;
        TeleFault = teleFault;
        this.ADSLFault = ADSLFault;
        this.UGFault = UGFault;
        RateV = rateV;
        RateD = rateD;
    }

    public String getTelephone_No() {
        return Telephone_No;
    }

    public void setTelephone_No(String telephone_No) {
        Telephone_No = telephone_No;
    }

    public String getTechnicianIDV() {
        return TechnicianIDV;
    }

    public void setTechnicianIDV(String technicianIDV) {
        TechnicianIDV = technicianIDV;
    }

    public String getTechnicianIDD() {
        return TechnicianIDD;
    }

    public void setTechnicianIDD(String technicianIDD) {
        TechnicianIDD = technicianIDD;
    }

    public String getLastCab() {
        return LastCab;
    }

    public void setLastCab(String lastCab) {
        LastCab = lastCab;
    }

    public String getFaultType() {
        return FaultType;
    }

    public void setFaultType(String faultType) {
        FaultType = faultType;
    }

    public String getJobNo() {
        return JobNo;
    }

    public void setJobNo(String jobNo) {
        JobNo = jobNo;
    }

    public String getFaultConnectionType() {
        return FaultConnectionType;
    }

    public void setFaultConnectionType(String faultConnectionType) {
        FaultConnectionType = faultConnectionType;
    }

    public String getSubmitDate() {
        return SubmitDate;
    }

    public void setSubmitDate(String submitDate) {
        SubmitDate = submitDate;
    }

    public String getSubmitTime() {
        return SubmitTime;
    }

    public void setSubmitTime(String submitTime) {
        SubmitTime = submitTime;
    }

    public String getPassTime() {
        return PassTime;
    }

    public void setPassTime(String passTime) {
        PassTime = passTime;
    }

    public String getPassDate() {
        return PassDate;
    }

    public void setPassDate(String passDate) {
        PassDate = passDate;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getCabinetNo() {
        return CabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        CabinetNo = cabinetNo;
    }

    public boolean isTeleFault() {
        return TeleFault;
    }

    public void setTeleFault(boolean teleFault) {
        TeleFault = teleFault;
    }

    public boolean isADSLFault() {
        return ADSLFault;
    }

    public void setADSLFault(boolean ADSLFault) {
        this.ADSLFault = ADSLFault;
    }

    public boolean isUGFault() {
        return UGFault;
    }

    public void setUGFault(boolean UGFault) {
        this.UGFault = UGFault;
    }

    public float getRateV() {
        return RateV;
    }

    public void setRateV(float rateV) {
        RateV = rateV;
    }

    public float getRateD() {
        return RateD;
    }

    public void setRateD(float rateD) {
        RateD = rateD;
    }
}