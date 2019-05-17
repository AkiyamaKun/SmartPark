package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.CameraDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Camera;
import Core.Entity.ParkingLot;
import Core.Repository.CameraRepository;
import Core.Repository.ParkingLotRepository;
import Core.Service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class CameraImpl implements CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

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
        dto.setParkingLotId(entity.getParkingLotId().getParkingLotId());
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
    public ResponseDTO getCameraByParkingLotId(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        Map<Integer, String> message = new HashMap<>();
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if (parkingLot != null) {
                Camera camera = cameraRepository.findByCameraId(1);
                if (camera.getUrlLiveStream() != null) {
                    String urlStr = camera.getUrlLiveStream();
                    message.put(2, urlStr);
                    responseDTO.setMapMessage(message);
                }
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_PARKING_LOT_SUCCESS);
                responseDTO.setObjectResponse(parkingLot);
            } else {
                responseDTO.setMessage(Const.GET_PARKING_LOT_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get Parking Lot Exception: " + e.getMessage());
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

    @Override
    public ResponseDTO getListCameraOfParkingLot(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        Map<Integer, String> message = new HashMap<>();
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if (parkingLot != null) {
                List<Camera> cameras = cameraRepository.findAllByParkingLotId(parkingLot);
                cameras.forEach(e -> {
                    if (e.getUrlLiveStream() != null) {
                        String urlStr = e.getUrlLiveStream() + parkingLotId;
                        System.out.println(urlStr);
                        URL url = null;
                        try {
                            url = new URL(urlStr);
                            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                            urlConn.connect();
                            if (urlConn.getResponseCode() == 200) {
                                message.put(1, "active");
                                responseDTO.setMapMessage(message);
                            } else {
                                message.put(1, "not active");
                                responseDTO.setMapMessage(message);
                            }
                        } catch (MalformedURLException ex) {
                            ex.printStackTrace();
                        } catch (IOException o) {
                            o.printStackTrace();
                        }
                    }
                });
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_LIST_CAMERA_OF_PARKING_LOT_SUCCESS);
                responseDTO.setObjectResponse(cameras);
            } else {
                responseDTO.setMessage(Const.GET_LIST_CAMERA_OF_PARKING_LOT_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get List Camera Of Parking Lot Exception: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * @param cameraDTO
     * @return
     */
    @Override
    public ResponseDTO createCamera(CameraDTO cameraDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            CameraDTO responseCameraDTO = new CameraDTO();
            Camera camera = new Camera();
            camera.setCameraName(cameraDTO.getCameraName());
            camera.setIpAddress(cameraDTO.getIpAddress());
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(cameraDTO.getParkingLotId());
            camera.setParkingLotId(parkingLot);
            camera.setActive(true);
            cameraRepository.save(camera);
            convertDTOFromEntity(cameraDTO, camera);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.CREATE_CAMERA_SUCCESS);
            responseDTO.setObjectResponse(responseCameraDTO);
        } catch (Exception e) {
            responseDTO.setMessage(Const.CREATE_CAMERA_FAIL);
        }
        return responseDTO;
    }

    /**
     * @param cameraId
     * @param dto
     * @return
     */
    @Override
    public ResponseDTO updateCamera(Integer cameraId, CameraDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Camera camera = cameraRepository.findByCameraId(cameraId);
            if (camera != null) {
                camera.setCameraName(dto.getCameraName());
                camera.setIpAddress(dto.getIpAddress());
                cameraRepository.save(camera);
                CameraDTO responseCameraDTO = new CameraDTO();
                convertDTOFromEntity(responseCameraDTO, camera);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.UPDATE_CAMERA_SUCCESS);
                responseDTO.setObjectResponse(responseCameraDTO);
            } else {
                responseDTO.setMessage(Const.CAMERA_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.UPDATE_CAMERA_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO assignCameraForParkingLot(Integer cameraId, Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Camera camera = cameraRepository.findByCameraId(cameraId);
            if (camera != null) {
                ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
                camera.setParkingLotId(parkingLot);
                cameraRepository.save(camera);
                CameraDTO responseCameraDTO = new CameraDTO();
                convertDTOFromEntity(responseCameraDTO, camera);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.ASSIGN_CAMERA_FOR_PARKING_LOT_SUCCESS);
                responseDTO.setObjectResponse(responseCameraDTO);
            } else {
                responseDTO.setMessage(Const.CAMERA_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.ASSIGN_CAMERA_FOR_PARKING_LOT_FAIL);
        }
        return responseDTO;
    }
}
