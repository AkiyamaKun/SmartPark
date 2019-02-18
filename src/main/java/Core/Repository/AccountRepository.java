package Core.Repository;

import Core.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Account Repository
 *
 * Author: DangNHH - 19/02/2019
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountId(Integer id);
    Account findByEmail(String email);
    Account findByEmailAndPassword(String email, String password);
}
