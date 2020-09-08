package shehan.com.sltphonelinerepairing;

public class Client {
    private String Telephone_No, NIC, contact_No, Name, Address, DP_NO, CabinetNo, LoopNo, ConnectionType;
    private Double Latitude,Longitude;
    public Client() {
    }

    public Client(String telephone_No, String nIC, String contact_No, String name, String address, String DP_NO, String cabinetNo, String loopNo, String connectionType, Double latitude, Double longitude) {
        Telephone_No = telephone_No;
        NIC = nIC ;
        this.contact_No = contact_No;
        Name = name;
        Address = address;
        this.DP_NO = DP_NO;
        CabinetNo = cabinetNo;
        LoopNo = loopNo;
        ConnectionType = connectionType;
        Latitude = latitude;
        Longitude = longitude;
    }

    public String getTelephone_No() {
        return Telephone_No;
    }

    public void setTelephone_No(String telephone_No) {
        Telephone_No = telephone_No;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String nIC) {
        NIC = nIC ;
    }

    public String getContact_No() {
        return contact_No;
    }

    public void setContact_No(String contact_No) {
        this.contact_No = contact_No;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDP_NO() {
        return DP_NO;
    }

    public void setDP_NO(String DP_NO) {
        this.DP_NO = DP_NO;
    }

    public String getCabinetNo() {
        return CabinetNo;
    }

    public void setCabinetNo(String cabinetNo) {
        CabinetNo = cabinetNo;
    }

    public String getLoopNo() {
        return LoopNo;
    }

    public void setLoopNo(String loopNo) {
        LoopNo = loopNo;
    }

    public String getConnectionType() {
        return ConnectionType;
    }

    public void setConnectionType(String connectionType) {
        ConnectionType = connectionType;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }
}
