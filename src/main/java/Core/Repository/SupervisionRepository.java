package Core.Repository;

import Core.Entity.Account;
import Core.Entity.Supervision;
import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisionRepository extends JpaRepository<Supervision, Integer> {
    Supervision findBySupervisionId(Integer id);
    Supervision findByParkingLot(ParkingLot parkingLot);
    Supervision findBySupervisor(Account supervisor);
}
