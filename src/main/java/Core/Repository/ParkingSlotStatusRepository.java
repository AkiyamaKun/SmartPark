package Core.Repository;

import Core.Entity.ParkingSlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotStatusRepository extends JpaRepository<ParkingSlotStatus, Integer> {
    ParkingSlotStatus findByStatusName(String name);
    ParkingSlotStatus findByStatusId(Integer id);
}
