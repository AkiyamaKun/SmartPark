package Core.Repository;

import Core.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findByTransactionId(Integer transactionId);
}
