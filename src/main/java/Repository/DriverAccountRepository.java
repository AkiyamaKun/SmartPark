package Repository;

import Entity.DriverAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Driver Account Controller
 *
 * Author: DangNHH - 16/02/2019
 */
@Repository
public interface DriverAccountRepository extends JpaRepository<DriverAccount, Integer> {
    DriverAccount findByDriverID(Integer id);
    DriverAccount findByName(String name);
    DriverAccount findByPhoneNumber(String phoneNumber);
    DriverAccount findByEmail(String email);
    DriverAccount findByEmailAndPassword(String email, String password);
}
