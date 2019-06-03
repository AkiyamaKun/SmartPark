package Core.Repository;

import Core.Entity.ParkingLot;
import Core.Entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Parking Slot Repository
 *
 * Author: DangNHH - 19/02/2019
 */
@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Integer> {
    ParkingSlot findBySlotId(Integer slotId);
    List<ParkingSlot> findByParkingLot(ParkingLot parkingLot);
    List<ParkingSlot> findByParkingSlotStatus_StatusName(String status);

    List<ParkingSlot> findByParkingSlotStatus_StatusNameAndParkingLot_ParkingLotId(String status, Integer parkingLotId);
}
