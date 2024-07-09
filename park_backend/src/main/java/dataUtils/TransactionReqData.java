package dataUtils;

import com.scheidtbachmann.phfa.digitalproduct.zrconnector.model.RequestDataDTO;

import java.util.List;
import java.util.Objects;




public class TransactionReqData {

	private long transactionId;
    private String clientId;
    private String correlationId;
    private String mediumType = "barcode";
    private String mediumValue = "";
    private String licencePlate; // for late payment
    private List<String> present = null;
    private List<String> latePayments = null;

    public TransactionReqData() {}

    public TransactionReqData(RequestDataDTO dtoData, String correlationId) {
        if (dtoData != null) {
            clientId = dtoData.getClientId();
            mediumType = dtoData.getMediumType();
            mediumValue = dtoData.getMediumValue();

            if (dtoData.getLicensePlate() != null && !dtoData.getLicensePlate().isEmpty())
                licencePlate = dtoData.getLicensePlate();
            else if (dtoData.getMediumType() != null &&  dtoData.getMediumType().equalsIgnoreCase("lpn"))
                licencePlate = dtoData.getMediumValue();
            else
                licencePlate = null;

            present = dtoData.getPresent();
            latePayments = dtoData.getLatePayments();
        }
        this.correlationId = correlationId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getMediumType() {
        return mediumType;
    }

    public void setMediumType(String mediumType) {
        this.mediumType = mediumType;
    }

    public String getMediumValue() {
        return mediumValue;
    }

    public void setMediumValue(String mediumValue) {
        this.mediumValue = mediumValue;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public List<String> getPresent() {
        return present;
    }

    public void setPresent(List<String> present) {
        this.present = present;
    }

    public List<String> getLatePayments() {
        return latePayments;
    }

    public void setLatePayments(List<String> latePayments) {
        this.latePayments = latePayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionReqData)) return false;
        TransactionReqData that = (TransactionReqData) o;
        return Objects.equals(getClientId(), that.getClientId()) && Objects.equals(getCorrelationId(), that.getCorrelationId()) && Objects.equals(getMediumType(), that.getMediumType()) && Objects.equals(getMediumValue(), that.getMediumValue()) && Objects.equals(getPresent(), that.getPresent()) && Objects.equals(getLatePayments(), that.getLatePayments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getCorrelationId(), getMediumType(), getLicencePlate(), getMediumValue(), getPresent(), getLatePayments());
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "clientId='" + clientId + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", mediumType='" + mediumType + '\'' +
                ", mediumValue='" + mediumValue + '\'' +
                ", licencePlate='" + licencePlate + '\'' +
                ", present=" + present +
                ", latePayments=" + latePayments +
                '}';
    }
}
