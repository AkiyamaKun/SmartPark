package Core.Repository;

import Core.Entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Parking Slot Repository
 *
 * Author: DangNHH - 19/02/2019
 */
@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {
}
