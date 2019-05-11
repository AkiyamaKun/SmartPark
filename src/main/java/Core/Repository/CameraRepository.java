package Core.Repository;

import Core.Entity.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Integer> {

    Camera findByCameraId(Integer cameraId);
}
