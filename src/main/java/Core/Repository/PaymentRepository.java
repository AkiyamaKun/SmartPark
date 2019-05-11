package Core.Repository;

import Core.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByPaymentId(Integer paymentId);
}
