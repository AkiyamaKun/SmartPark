package Core.Repository;

import Core.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findByTransactionId(Integer transactionId);

    @Query("SELECT SUM(t.money) FROM Transaction t")
    Integer totalMoney();
}
