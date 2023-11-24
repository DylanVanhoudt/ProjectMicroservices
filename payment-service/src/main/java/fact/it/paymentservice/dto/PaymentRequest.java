package fact.it.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private String appointmentNumber;
    private boolean isPaymentSuccessful;
    private String paymentMethod; // The payment method used (e.g., credit card, PayPal)
    private double amountPaid;    // The amount paid for the transaction
    private String currency;      // The currency in which the payment was made
}
