package Core.Repository;

import Core.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findByTransactionId(Integer transactionId);

    Transaction findByAccountId_AccountId(Integer accountId);

    List<Transaction> findByBookingId_BookingId(Integer bookingId);

    @Query("SELECT SUM(t.money) FROM Transaction t")
    Integer totalMoney();

    List<Transaction> findByAccountId_AccountIdOrderByTransactionIdDesc(Integer accountId);
}
