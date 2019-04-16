package Core.Repository;

import Core.Entity.ParkingSlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotStatusRepository extends JpaRepository<ParkingSlotStatus, Integer> {
    List<ParkingSlotStatus> findByStatusName(String name);
}
