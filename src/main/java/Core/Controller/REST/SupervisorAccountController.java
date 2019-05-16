package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.ParkingLotUpdateDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.ParkingLot;
import Core.Service.AccountService;
import Core.Service.CameraService;
import Core.Service.ParkingLotService;
import Core.Service.SupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = Const.SUPERVISOR_ACCOUNT)
public class SupervisorAccountController {

    @Autowired
    ParkingLotService parkingLotService;

    @Autowired
    SupervisionService supervisionService;

    @Autowired
    AccountService accountService;

    @Autowired
    CameraService cameraService;

    /**
     * Get List Parking Lot Control By Supervisor
     * @param supervisorId
     * @return
     */
    @RequestMapping(value = Const.LIST_PARKINGLOT_CONTROL_BY_SUPERVISOR, method = RequestMethod.GET)
    public ResponseDTO getListParkingLotControlBySupervisor(@RequestParam(value = "supervisorId") Integer supervisorId){
        ResponseDTO responseDTO = parkingLotService.getListParkingLotControlBySupervisor(supervisorId);
        List<ParkingLot> parkingLots = (List<ParkingLot>) responseDTO.getObjectResponse();
        List<ParkingLot> parkingLotActive = new ArrayList<>();
        for(ParkingLot item: parkingLots){
            if(item.isActive()){
                parkingLotActive.add(item);
            }
        }
        responseDTO.setObjectResponse(parkingLotActive);
        return responseDTO;
    }

    /**
     * Update Parking Lot
     * @param dto
     * @param accountId
     * @return
     */
    @RequestMapping(value = Const.UPDATE_PARKING_LOT, method = RequestMethod.PUT)
    public ResponseDTO updateParkingLot(@RequestBody @Valid ParkingLotUpdateDTO dto,
                                        @PathVariable Integer accountId){
        return parkingLotService.updateParkingLot(dto, accountId);
    }

    /**
     * Get List Camera
     *
     * @return
     */
    @RequestMapping(value = Const.LIST_CAMERA, method = RequestMethod.GET)
    public ResponseDTO getListCamera(){
        return cameraService.getAllCamera();
    }

    /**
     * Get List Camera
     *
     * @return
     */
    @RequestMapping(value = Const.LIST_CAMERA_BY_PARKINGLOTID, method = RequestMethod.GET)
    public ResponseDTO getListCameraByParkingLotId(@PathVariable Integer parkingLotId){
        return cameraService.getListCameraOfParkingLot(parkingLotId);
    }
}
