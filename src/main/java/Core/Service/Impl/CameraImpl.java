package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.CameraDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Camera;
import Core.Repository.CameraRepository;
import Core.Service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CameraImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    /**
     * Function Convert DTO From Entity
     *
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(CameraDTO dto, Camera entity) {
        dto.setCameraId(entity.getCameraId());
        dto.setCameraName(entity.getCameraName());
        dto.setIpAddress(entity.getIpAddress());
        dto.setParkingLotId(entity.getParkingLotId());
        dto.setActive(entity.isActive());
    }

    @Override
    public ResponseDTO getCamera(Integer cameraId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Camera camera = cameraRepository.findByCameraId(cameraId);
            CameraDTO dto = new CameraDTO();
            if (camera != null) {
                convertDTOFromEntity(dto, camera);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_CAMERA_SUCCESS);
                responseDTO.setObjectResponse(dto);
            } else {
                responseDTO.setMessage(Const.CAMERA_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get camera is exception: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getAllCamera() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<CameraDTO> cameraDTOS = new ArrayList<>();
            List<Camera> cameras = cameraRepository.findAll();
            for (Camera camera : cameras) {
                CameraDTO tmp = new CameraDTO();
                convertDTOFromEntity(tmp, camera);
                cameraDTOS.add(tmp);
            }
            if (!cameraDTOS.isEmpty()) {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_LIST_CAMERA_SUCCESS);
                responseDTO.setObjectResponse(cameraDTOS);
            } else {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.GET_LIST_CAMERA_FAIL);
        }
        return responseDTO;
    }
}
