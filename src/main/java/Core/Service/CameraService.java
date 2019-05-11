package Core.Service;

import Core.DTO.ResponseDTO;

public interface CameraService {

    ResponseDTO getCamera(Integer cameraId);

    ResponseDTO getAllCamera();
}
