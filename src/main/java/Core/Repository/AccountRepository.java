package Core.Repository;

import Core.Entity.Account;
import Core.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<Account> findAllByRole(Role role);
}
