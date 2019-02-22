package Core.Repository;

import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Parking Lot Repository
 *
 * Author: DangNHH - 19/02/2019
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {
}
