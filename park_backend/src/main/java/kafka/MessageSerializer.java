package kafka;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageSerializer {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Message<ConsultRequest> decode(Map<String, Object> encodedEnvelope) {
        Map<String, String> record = (Map<String, String>) encodedEnvelope.get("body");
        ConsultRequest consultObject = new ConsultRequest();

        if (record != null) {
            consultObject.setClientId(fieldValidator(record, "clientId"));
            consultObject.setTransactionId(fieldValidator(record, "transactionId"));
            consultObject.setMediumType(fieldValidator(record, "mediumType"));
            consultObject.setMediumValue(fieldValidator(record, "mediumValue"));
            consultObject.setLicensePlate(fieldValidator(record, "licensePlate"));
            consultObject.setTransactionType(fieldValidator(record, "transactionType"));
            consultObject.setEpan(fieldValidator(record, "epan"));
            consultObject.setOperator(fieldValidator(record, "operator"));
            consultObject.setCellComputer(Integer.valueOf(fieldValidator(record, "cellComputer")));
            consultObject.setFacilityId(Integer.valueOf(fieldValidator(record, "facilityId")));
            consultObject.setAmount(Integer.valueOf(fieldValidator(record, "amount")));
            consultObject.setCurrency(fieldValidator(record, "currency"));
            consultObject.setVatType(fieldValidator(record, "vatType"));
            consultObject.setVatValue(fieldValidator(record, "vatValue"));
            consultObject.setTenant(fieldValidator(record, "tenant"));
            consultObject.setDuration(fieldValidator(record, "duration"));
            consultObject.setTariffTimeStart(fieldValidator(record, "tariffTimeStart"));
            consultObject.setTariffTimeEnd(fieldValidator(record, "tariffTimeEnd"));
            consultObject.setArticleShortText(fieldValidator(record, "articleShortText"));
        }

        return MessageBuilder
                .withPayload(consultObject)
                .copyHeaders(encodedEnvelope)
                .build();
    }

    public Message<?> encode(Message<ConsultRequest> envelope) {
        ConsultRequest event = envelope.getPayload();
        Map<String, Object> record = new HashMap<>();
        record.put("clientId",event.getClientId());
        record.put("transactionId",event.getTransactionId());
        record.put("mediumType", event.getMediumType());
        record.put("mdiumValue", event.getMediumValue());
        record.put("licensePlate", event.getLicensePlate());
        record.put("transactionType", event.getTransactionType());
        record.put("epan", event.getEpan());
        record.put("operator", event.getOperator());
        record.put("cellComputer", event.getCellComputer());
        record.put("facilityId", event.getFacilityId());
        record.put("amount", event.getAmount());
        record.put("currency", event.getCurrency());
        record.put("vatType", event.getVatType());
        record.put("vatValue", event.getVatValue());
        record.put("tenant", event.getTenant());
        record.put("duration", event.getDuration());
        record.put("tariffTimeStart", event.getTariffTimeStart());
        record.put("tariffTimeEnd", event.getTariffTimeEnd());
        record.put("articleShortText", event.getArticleShortText());
       
        return MessageBuilder
                .withPayload(record)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .copyHeaders(envelope.getHeaders())
                .build();
    }

    private String fieldValidator(Map<String, String> data, String key) {
        return data.containsKey(key) ? data.get(key) : "";
    }
}

