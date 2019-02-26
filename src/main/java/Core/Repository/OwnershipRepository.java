package Core.Repository;

import Core.Entity.Account;
import Core.Entity.Ownership;
import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnershipRepository extends JpaRepository<Ownership, Integer> {
    Ownership findByOwnershipId(Integer id);
    Ownership findByParkingLot(ParkingLot parkingLot);
    Ownership findBySupervisor(Account supervisor);
}
