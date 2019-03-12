package Core.Repository;

import Core.Entity.Account;
import Core.Entity.Supervision;
import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupervisionRepository extends JpaRepository<Supervision, Integer> {
    Supervision findBySupervisionId(Integer id);
    List<Supervision> findByParkingLot(ParkingLot parkingLot);
    List<Supervision> findBySupervisor(Account supervisor);
}
