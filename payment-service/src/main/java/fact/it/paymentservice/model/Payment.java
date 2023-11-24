package fact.it.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentNumber;
    private String appointmentNumber;
    private boolean isPaymentSuccessful;
    private String paymentMethod; // The payment method used (e.g., credit card, PayPal)
    private double amountPaid;    // The amount paid for the transaction
    private String currency;      // The currency in which the payment was made
}
