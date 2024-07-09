package DBEntity;

import javax.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class PaymentTransactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "paymentTransactions")
    private List<ConsultTransaction> transactions = new ArrayList<>();

    @Column(length = 255, nullable = true)
    private String shoppingCartUuid;

    @Column(length = 255, nullable = true)
    private String mediaType;

    @Column(length = 255, nullable = true)
    private String payId;

    @Column(length = 255, nullable = true)
    private String maskedMediaId;

    @Column(length = 255, nullable = true)
    private String statusPayment;

    @Column(length = 255, nullable = true)
    private String reference;

    @Column(length = 255, nullable = true)
    private String correlationId;

    @Column(length = 255, nullable = true)
    private String email;

    @Column
    private boolean status;

    @Column(nullable = true)
    private LocalDateTime paymentDate;

    @Column(nullable = true)
    private String locale;

    @Column(nullable = true)
    private String receiptNumber;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private String localeFormat;

    @Column(nullable = true, columnDefinition = "boolean default false")
    private boolean emailIsSent;

    public void addTransaction(ConsultTransaction transaction) {
        transactions.add(transaction);
        //transaction.setPaymentTransactions(this);
    }
}
