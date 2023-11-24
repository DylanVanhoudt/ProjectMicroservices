package fact.it.paymentservice.service;

import fact.it.paymentservice.dto.PaymentRequest;
import fact.it.paymentservice.dto.PaymentResponse;
import fact.it.paymentservice.model.Payment;
import fact.it.paymentservice.repository.PaymentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @PostConstruct
    public void loadData() {
        if (paymentRepository.count() <= 0) {
            Payment payment1 = new Payment();
            payment1.setPaymentNumber("PAY001");
            payment1.setAppointmentNumber("A001");
            payment1.setPaymentSuccessful(true);
            payment1.setPaymentMethod("Credit Card");
            payment1.setAmountPaid(100.00);
            payment1.setCurrency("USD");

            Payment payment2 = new Payment();
            payment2.setPaymentNumber("PAY002");
            payment2.setAppointmentNumber("A002");
            payment2.setPaymentSuccessful(false);
            payment2.setPaymentMethod("PayPal");
            payment2.setAmountPaid(50.00);
            payment2.setCurrency("EUR");

            paymentRepository.save(payment1);
            paymentRepository.save(payment2);
        }
    }

    public void createPayment(PaymentRequest paymentRequest) {
        // Create a new Payment entity from the request data
        Payment newPayment = new Payment();
        newPayment.setPaymentNumber(UUID.randomUUID().toString());
        newPayment.setAppointmentNumber(paymentRequest.getAppointmentNumber());
        newPayment.setPaymentSuccessful(paymentRequest.isPaymentSuccessful());
        newPayment.setPaymentMethod(paymentRequest.getPaymentMethod());
        newPayment.setAmountPaid(paymentRequest.getAmountPaid());
        newPayment.setCurrency(paymentRequest.getCurrency());

        // Save the new payment to the repository
        paymentRepository.save(newPayment);
    }

    public List<PaymentResponse> getAllPayments() {
        // Retrieve all payments from the repository
        List<Payment> payments = paymentRepository.findAll();

        // Convert payment entities to response objects
        return payments.stream()
                .map(this::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    public List<PaymentResponse> getAllPaymentsByAppointmentNumber(String appointmentNumber) {
        // Retrieve payments for a specific appointment number from the repository
        List<Payment> payments = paymentRepository.findByAppointmentNumber(appointmentNumber);

        // Convert payment entities to response objects
        return payments.stream()
                .map(this::mapToPaymentResponse)
                .collect(Collectors.toList());
    }

    public void updatePayment(String paymentNumber, PaymentRequest paymentRequest) {
        // Retrieve the payment by its number from the repository
        Payment existingPayment = paymentRepository.findByPaymentNumber(paymentNumber)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Update the payment with the new data from the request
        existingPayment.setAppointmentNumber(paymentRequest.getAppointmentNumber());
        existingPayment.setPaymentSuccessful(paymentRequest.isPaymentSuccessful());
        existingPayment.setPaymentMethod(paymentRequest.getPaymentMethod());
        existingPayment.setAmountPaid(paymentRequest.getAmountPaid());
        existingPayment.setCurrency(paymentRequest.getCurrency());

        // Save the updated payment to the repository
        paymentRepository.save(existingPayment);
    }

    private PaymentResponse mapToPaymentResponse(Payment payment) {
        // Convert a Payment entity to a PaymentResponse object
        return PaymentResponse.builder()
                .paymentNumber(payment.getPaymentNumber())
                .appointmentNumber(payment.getAppointmentNumber())
                .isPaymentSuccessful(payment.isPaymentSuccessful())
                .paymentMethod(payment.getPaymentMethod())
                .amountPaid(payment.getAmountPaid())
                .currency(payment.getCurrency())
                .build();
    }
}
