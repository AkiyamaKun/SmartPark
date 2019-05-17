package Core.Repository;

import Core.Entity.Camera;
import Core.Entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CameraRepository extends JpaRepository<Camera, Integer> {

    Camera findByCameraId(Integer cameraId);

    List<Camera> findAllByParkingLotId(ParkingLot parkingLot);

    Camera findByParkingLotId(ParkingLot parkingLot);
}
