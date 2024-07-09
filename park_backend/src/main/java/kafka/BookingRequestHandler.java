package kafka;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.schiedtandbachmann.CommonInfoHelper;

import DBEntity.ConsultTransaction;
import DBEntity.PaymentTransactions;
import data_cache.data.CellComputerDetail;
import repository.ConsultTransactionRepository;
import repository.PaymentTransactionRepository;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class BookingRequestHandler {
@Autowired
ConsultTransactionRepository rep;
@Autowired 
PaymentTransactionRepository repP;
@Autowired 
CommonInfoHelper commonInfoHelper;

	public Object processBooking(BookingRequest obj) {
        try {
            // Fetch transaction from the database
        	String transactionId = obj.getTransactionId();
            ConsultTransaction transaction = rep.findOneByTransactionIdOrderByIdDesc(transactionId);

            if (transaction != null) {
                // Update transaction properties
                transaction.setStatus(obj.getStatus());
                transaction.setVatType(obj.getVatType());
                transaction.setVatValue(obj.getVatValue());
                transaction.setArticleShortText(obj.getArticleShortText());
                transaction.setArticleRef(obj.getArticleRef());
                transaction.setOriginalAmount(obj.getOriginalAmount());

                LocalDateTime bookingDate = (obj.getDate());
                transaction.setPaymentDate(bookingDate);

                PaymentTransactions paymentTransactions = transaction.getPaymentTransactions();

                String locationId = transaction.getOperatorId() + transaction.getCellComputerId();
                CellComputerDetail parking = commonInfoHelper.getCellComputerByLocationId(locationId);
                String offset = (parking != null && parking.getOffsetUTC() != null) ? parking.getOffsetUTC() : "+02:00";
                ZoneId zoneId = ZoneId.of(offset);
                ZoneOffset zoneOffset = ZoneOffset.of(offset);
                LocalDateTime currentDateTime = LocalDateTime.now();
                ZonedDateTime zonedDateTime = currentDateTime.atZone(zoneId).withZoneSameInstant(zoneOffset);
                LocalDateTime createdAt = zonedDateTime.toLocalDateTime();

                paymentTransactions.setCreatedAt(createdAt);

              //  loggerService.create("kafka_booking_transaction", "created date " + createdAt.toString());

                if (obj.getReceiptNumber() != null) {
                    paymentTransactions.setReceiptNumber(obj.getReceiptNumber());
                    transaction.setBookingId(obj.getBookingId());
                }

                // Update PaymentTransactions
                rep.save(transaction);
                repP.save(paymentTransactions);

                
                // Send email
                consultTransactionNotified(transaction);
            }

            return true;
        } catch (Exception e) {
            //loggerService.create("kafka_booking_transaction_error_database", e.getMessage());
            return e.getMessage();
        } 
    }

    private void consultTransactionNotified(ConsultTransaction transaction) {
        // Implementation of email notification 
    	}
}
