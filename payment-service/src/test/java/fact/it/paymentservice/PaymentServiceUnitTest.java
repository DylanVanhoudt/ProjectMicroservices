package fact.it.paymentservice;

import fact.it.paymentservice.dto.PaymentRequest;
import fact.it.paymentservice.dto.PaymentResponse;
import fact.it.paymentservice.model.Payment;
import fact.it.paymentservice.repository.PaymentRepository;
import fact.it.paymentservice.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceUnitTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    public void testCreatePayment() {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAppointmentNumber("A003");
        paymentRequest.setPaymentSuccessful(true);
        paymentRequest.setPaymentMethod("Credit Card");
        paymentRequest.setAmountPaid(75.00);
        paymentRequest.setCurrency("USD");

        // Act
        paymentService.createPayment(paymentRequest);

        // Assert
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    public void testGetAllPayments() {
        // Arrange
        Payment payment1 = createTestPayment("PAY001", "A001", true, "Credit Card", 100.00, "USD");
        Payment payment2 = createTestPayment("PAY002", "A002", false, "PayPal", 50.00, "EUR");

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment1, payment2));

        // Act
        List<PaymentResponse> payments = paymentService.getAllPayments();

        // Assert
        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllPaymentsByAppointmentNumber() {
        // Arrange
        Payment payment1 = createTestPayment("PAY001", "A001", true, "Credit Card", 100.00, "USD");
        Payment payment2 = createTestPayment("PAY002", "A001", false, "PayPal", 50.00, "EUR");

        when(paymentRepository.findByAppointmentNumber("A001")).thenReturn(Arrays.asList(payment1, payment2));

        // Act
        List<PaymentResponse> payments = paymentService.getAllPaymentsByAppointmentNumber("A001");

        // Assert
        assertEquals(2, payments.size());
        verify(paymentRepository, times(1)).findByAppointmentNumber("A001");
    }

    @Test
    public void testUpdatePayment() {
        // Arrange
        Payment existingPayment = createTestPayment("PAY003", "A003", true, "Credit Card", 75.00, "USD");
        PaymentRequest updatedPaymentRequest = new PaymentRequest();
        updatedPaymentRequest.setAppointmentNumber("A004");
        updatedPaymentRequest.setPaymentSuccessful(false);
        updatedPaymentRequest.setPaymentMethod("PayPal");
        updatedPaymentRequest.setAmountPaid(50.00);
        updatedPaymentRequest.setCurrency("EUR");

        when(paymentRepository.findByPaymentNumber("PAY003")).thenReturn(Optional.of(existingPayment));

        // Act
        paymentService.updatePayment("PAY003", updatedPaymentRequest);

        // Assert
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals("A004", existingPayment.getAppointmentNumber());
        assertFalse(existingPayment.isPaymentSuccessful());
        assertEquals("PayPal", existingPayment.getPaymentMethod());
        assertEquals(50.00, existingPayment.getAmountPaid());
        assertEquals("EUR", existingPayment.getCurrency());
    }

    private Payment createTestPayment(String paymentNumber, String appointmentNumber, boolean paymentSuccessful,
                                      String paymentMethod, double amountPaid, String currency) {
        Payment payment = new Payment();
        payment.setPaymentNumber(paymentNumber);
        payment.setAppointmentNumber(appointmentNumber);
        payment.setPaymentSuccessful(paymentSuccessful);
        payment.setPaymentMethod(paymentMethod);
        payment.setAmountPaid(amountPaid);
        payment.setCurrency(currency);
        return payment;
    }
}
