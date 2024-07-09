package DBEntity;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ConsultTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String clientId;

    private String transactionId;

    private String mediumType;

    private String mediumValue;

    private String licensePlate;

    private String type;

    private String epan;

    private String operatorId;

    private Integer cellComputerId;

    private Integer facilityId;

    private Integer amount;

    private String originalAmount;

    
    private String currency;

    private String vatType;

    private String vatValue;

    private String tenant;

    private String duration;

    private String tariffTimeStart;

    private String tariffTimeEnd;

    private LocalDateTime paymentDate;

    private String articleShortText;

    private String articleRef;

    private String status;

    private String bookingId;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "payment_transactions_id")
    private PaymentTransactions paymentTransactions;

    
}
