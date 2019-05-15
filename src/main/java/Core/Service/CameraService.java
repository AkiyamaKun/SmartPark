package Core.Service;

import Core.DTO.CameraDTO;
import Core.DTO.ResponseDTO;

public interface CameraService {

    ResponseDTO getCamera(Integer cameraId);

    ResponseDTO getAllCamera();

    ResponseDTO getListCameraOfParkingLot(Integer parkingLotId);

    ResponseDTO createCamera(CameraDTO cameraDTO);

    ResponseDTO updateCamera(Integer cameraId, CameraDTO dto);

    ResponseDTO assignCameraForParkingLot(Integer cameraId, Integer parkingLotId);
}
