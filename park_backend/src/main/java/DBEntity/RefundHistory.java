package DBEntity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "refund_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefundHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String correlationId;

    @Column(length = 255)
    private String amount;

    @Column(length = 255)
    private String shoppingCartUuid;

    @Column(length = 255)
    private String tenant;

    @Column(length = 255)
    private String requestStatus;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

}
