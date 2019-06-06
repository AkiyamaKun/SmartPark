package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.ParkingLotUpdateDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.ParkingLot;
import Core.Service.*;
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

    @Autowired
    BookingService bookingService;

    @Autowired
    TransactionService transactionService;

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

    /**
     * Count booking slot
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.COUNT_BOOKING, method = RequestMethod.GET)
    public ResponseDTO countBookingSlotByStatus(@RequestParam Integer parkingLotId){
        return bookingService.countBookingSlotByStatus(parkingLotId);
    }

    /**
     * Get List booking for report
     *
     * @return
     */
    @RequestMapping(value = Const.LIST_BOOKING_FOR_REPORT, method = RequestMethod.GET)
    public ResponseDTO listBookingForReport(){
        return bookingService.listBookingForReport();
    }

    /**
     * Refund for driver
     *
     * @return
     */
    @RequestMapping(value = Const.REFUND_FOR_DRIVER, method = RequestMethod.PUT)
    public ResponseDTO refundForDriver(@RequestParam Integer parkingLotId){
        return transactionService.refundForDriver(parkingLotId);
    }
}
