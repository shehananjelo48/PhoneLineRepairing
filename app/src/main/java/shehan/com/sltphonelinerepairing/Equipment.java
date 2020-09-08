package shehan.com.sltphonelinerepairing;

public class Equipment {
    private String Tech_ID;
    private String Telephone,FaultId,TeleS,RouterS,TVboxS,RemoteS;
    private int TeleQ,DropWireQ,RosetBQ,SpilterQ,Drop_conQ,RosetCQ,L_hQ,C_hQ,ReternerQ,RouterQ,PeoBQ,RemoteQ;
    public Equipment(){

    }

    public Equipment(String tech_ID, String telephone, String faultId, String teleS, String routerS, String TVboxS, String remoteS, int teleQ, int dropWireQ, int rosetBQ, int spilterQ, int drop_conQ, int rosetCQ, int l_hQ, int c_hQ, int reternerQ, int routerQ, int peoBQ, int remoteQ) {
        Tech_ID = tech_ID;
        Telephone = telephone;
        FaultId = faultId;
        TeleS = teleS;
        RouterS = routerS;
        this.TVboxS = TVboxS;
        RemoteS = remoteS;
        TeleQ = teleQ;
        DropWireQ = dropWireQ;
        RosetBQ = rosetBQ;
        SpilterQ = spilterQ;
        Drop_conQ = drop_conQ;
        RosetCQ = rosetCQ;
        L_hQ = l_hQ;
        C_hQ = c_hQ;
        ReternerQ = reternerQ;
        RouterQ = routerQ;
        PeoBQ = peoBQ;
        RemoteQ = remoteQ;
    }

    public String getTech_ID() {
        return Tech_ID;
    }

    public void setTech_ID(String tech_ID) {
        Tech_ID = tech_ID;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getFaultId() {
        return FaultId;
    }

    public void setFaultId(String faultId) {
        FaultId = faultId;
    }

    public String getTeleS() {
        return TeleS;
    }

    public void setTeleS(String teleS) {
        TeleS = teleS;
    }

    public String getRouterS() {
        return RouterS;
    }

    public void setRouterS(String routerS) {
        RouterS = routerS;
    }

    public String getTVboxS() {
        return TVboxS;
    }

    public void setTVboxS(String TVboxS) {
        this.TVboxS = TVboxS;
    }

    public String getRemoteS() {
        return RemoteS;
    }

    public void setRemoteS(String remoteS) {
        RemoteS = remoteS;
    }

    public int getTeleQ() {
        return TeleQ;
    }

    public void setTeleQ(int teleQ) {
        TeleQ = teleQ;
    }

    public int getDropWireQ() {
        return DropWireQ;
    }

    public void setDropWireQ(int dropWireQ) {
        DropWireQ = dropWireQ;
    }

    public int getRosetBQ() {
        return RosetBQ;
    }

    public void setRosetBQ(int rosetBQ) {
        RosetBQ = rosetBQ;
    }

    public int getSpilterQ() {
        return SpilterQ;
    }

    public void setSpilterQ(int spilterQ) {
        SpilterQ = spilterQ;
    }

    public int getDrop_conQ() {
        return Drop_conQ;
    }

    public void setDrop_conQ(int drop_conQ) {
        Drop_conQ = drop_conQ;
    }

    public int getRosetCQ() {
        return RosetCQ;
    }

    public void setRosetCQ(int rosetCQ) {
        RosetCQ = rosetCQ;
    }

    public int getL_hQ() {
        return L_hQ;
    }

    public void setL_hQ(int l_hQ) {
        L_hQ = l_hQ;
    }

    public int getC_hQ() {
        return C_hQ;
    }

    public void setC_hQ(int c_hQ) {
        C_hQ = c_hQ;
    }

    public int getReternerQ() {
        return ReternerQ;
    }

    public void setReternerQ(int reternerQ) {
        ReternerQ = reternerQ;
    }

    public int getRouterQ() {
        return RouterQ;
    }

    public void setRouterQ(int routerQ) {
        RouterQ = routerQ;
    }

    public int getPeoBQ() {
        return PeoBQ;
    }

    public void setPeoBQ(int peoBQ) {
        PeoBQ = peoBQ;
    }

    public int getRemoteQ() {
        return RemoteQ;
    }

    public void setRemoteQ(int remoteQ) {
        RemoteQ = remoteQ;
    }
}
