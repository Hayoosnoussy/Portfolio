package kafka;

import java.util.Map;

import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.HashMap;
import java.util.Map;
@Service
public class BookingMessageSerializer {

	 String pattern = "yyyy-MM-dd HH:mm:ss"; // Adjust the pattern to match your input string format
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    private String fieldValidator(Map<String, String> data, String key) {
        return data.containsKey(key) ? data.get(key) : "";
    }

    public Message<BookingRequest> decode(Map<String, Object> encodedEnvelope) {
        Map<String, String> record = (Map<String, String>) encodedEnvelope.get("body");
        BookingRequest bookingObject = new BookingRequest();

        if (record != null) {
            bookingObject.setTransactionId(fieldValidator(record, "transactionId"));
           
            String Date = fieldValidator(record, "date");
            bookingObject.setDate(LocalDateTime.parse(Date, formatter));
            bookingObject.setReceiptNumber(fieldValidator(record, "receiptNumber"));
            bookingObject.setStatus(fieldValidator(record, "status"));
            bookingObject.setMessage(fieldValidator(record, "message"));
            bookingObject.setBookingId(fieldValidator(record, "bookingId"));
            bookingObject.setVatType(fieldValidator(record, "vatType"));
            bookingObject.setVatValue(fieldValidator(record, "vatValue"));
            bookingObject.setOriginalAmount(fieldValidator(record, "originalAmount"));
            bookingObject.setAmount(fieldValidator(record, "amount"));
            bookingObject.setClientId(fieldValidator(record, "clientId"));
            bookingObject.setArticleShortText(fieldValidator(record, "articleShortText"));
            bookingObject.setArticleRef(fieldValidator(record, "articleRef"));
        }

        return MessageBuilder
                .withPayload(bookingObject)
                .copyHeaders(encodedEnvelope)
                .build();
    }

    public Message<?> encode(Message<BookingRequest> envelope) throws JsonProcessingException {
        BookingRequest event = envelope.getPayload();

        Map<String, String> record = new HashMap<>();
        record.put("status", event.getStatus());
        record.put("transactionId", event.getTransactionId());
        String Date = formatter.format(event.getDate());
        record.put("date", Date);
        record.put("receiptNumber", event.getReceiptNumber());
        record.put("message", event.getMessage());
        record.put("bookingId", event.getBookingId());
        record.put("vatValue", event.getVatValue());
        record.put("vatType", event.getVatType());
        record.put("amount", event.getAmount());
        record.put("originalAmount", event.getOriginalAmount());
        record.put("clientId", event.getClientId());
        record.put("articleRef", event.getArticleRef());
        record.put("articleShortText", event.getArticleShortText());

        return MessageBuilder
                .withPayload(record)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .copyHeaders(envelope.getHeaders())
                .build();
    }
}

