package bean;

public class Ticket {
private int TENPO_ID;
private int TICKET_ID;
private String TICKET_NAME;
private int POINT;
public Ticket() { //コンストラクタ
super();
}
public Ticket(int TENPO_ID,int TICKET_ID,String TICKET_NAME,int POINT) { //コンストラクタ
setTeId(TENPO_ID);
setTiId(TICKET_ID);
setOptName(TICKET_NAME);
setPoint(POINT);
}
public int getTeId() {
return TENPO_ID;
}
public void setTeId(int TENPO_ID) {
this.TENPO_ID = TENPO_ID;
}
public int getTiId() {
return TENPO_ID;
}
public void setTiId(int TICKET_ID) {
this.TICKET_ID = TICKET_ID;
}
public String getOptName() {
return TICKET_NAME;
}
public void setOptName(String TICKET_NAME) {
this.TICKET_NAME = TICKET_NAME;
}
public int getPoint() {
return POINT;
}
public void setPoint(int point) {
this.POINT = POINT;
}
}



