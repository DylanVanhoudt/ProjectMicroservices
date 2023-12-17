package fact.it.paymentservice.controller;

import fact.it.paymentservice.dto.PaymentRequest;
import fact.it.paymentservice.dto.PaymentResponse;
import fact.it.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayment(@RequestBody PaymentRequest paymentRequest) {
        paymentService.createPayment(paymentRequest);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponse> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponse> getAllPaymentsByAppointmentNumber(@RequestParam String appointmentNumber) {
        return paymentService.getAllPaymentsByAppointmentNumber(appointmentNumber);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePayment(@RequestParam String paymentNumber, @RequestBody PaymentRequest paymentRequest) {
        paymentService.updatePayment(paymentNumber, paymentRequest);
    }
}
