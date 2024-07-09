package websocket.data;

//import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by kubanek.peter on 2. 6. 2022 for project ZRConnector.
 */
public class WebSocketMessage {
    private boolean parkingUnreachable;
    private boolean errorCode;
    private boolean transactionExist;
    private int graceTime;

    private String dataAction;

    private String correlationId;
    private Object data;

    public WebSocketMessage() {
    }

    public boolean getParkingUnreachable() {
        return parkingUnreachable;
    }

    public void setParkingUnreachable(boolean parkingUnreachable) {
        this.parkingUnreachable = parkingUnreachable;
    }

    public boolean getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(boolean errorCode) {
        this.errorCode = errorCode;
    }

    public boolean getTransactionExist() {
        return transactionExist;
    }

    public void setTransactionExist(boolean transactionExist) {
        this.transactionExist = transactionExist;
    }

    public int getGraceTime() {
        return graceTime;
    }

    public void setGraceTime(int graceTime) {
        this.graceTime = graceTime;
    }

    public String getDataAction() {
        return dataAction;
    }

    public void setDataAction(String dataAction) {
        this.dataAction = dataAction;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebSocketMessage)) return false;
        WebSocketMessage that = (WebSocketMessage) o;
        return getParkingUnreachable() == that.getParkingUnreachable() && Objects.equals(getErrorCode(), that.getErrorCode()) && Objects.equals(getTransactionExist(), that.getTransactionExist()) && Objects.equals(getDataAction(), that.getDataAction()) && Objects.equals(getCorrelationId(), that.getCorrelationId()) && Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkingUnreachable(), getErrorCode(), getTransactionExist(), getDataAction(), getCorrelationId(), getData());
    }

    @Override
    public String toString() {
        return "WebSocketMessage{" +
                "parkingUnreachable=" + parkingUnreachable +
                ", errorCode='" + errorCode + '\'' +
                ", transactionExist='" + transactionExist + '\'' +
                ", graceTime='" + graceTime + '\'' +
                ", dataAction='" + dataAction + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", data=" + data +
                '}';
    }
}
