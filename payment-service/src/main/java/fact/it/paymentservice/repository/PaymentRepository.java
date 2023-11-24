package fact.it.paymentservice.repository;

import fact.it.paymentservice.model.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByAppointmentNumber(String appointmentNumber);

    Optional<Payment> findByPaymentNumber(String paymentNumber);
}
