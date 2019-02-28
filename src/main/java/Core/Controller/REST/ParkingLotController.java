package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.ParkingLotDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Ownership;
import Core.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = Const.PARKING_LOT)
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    /**
     * Get Parking Lot by Id
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getParkingLot(@PathVariable Integer id){
        return parkingLotService.getParkingLot(id);
    }

    /**
     * Get list parking lots
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = Const.LIST_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListParkingLot() {
        return parkingLotService.getListParkingLot();
    }

    /**
     * Create New Parking Lot
     * @param dto
     * @param ownerId - Account of Supervisor who own this parking lot
     * @param adminId - Account of Admin - who create this parking lot
     * @return
     */
    @RequestMapping(value = Const.CREATE_PARKING_LOT, method = RequestMethod.POST)
    public ResponseDTO createParkingLot(@RequestBody @Valid ParkingLotDTO dto,
                                        @RequestParam (value = "ownerId", required = true) Integer ownerId,
                                        @RequestParam (value = "adminId", required = true) Integer adminId){
       return parkingLotService.createParkingLot(dto, ownerId, adminId );
    }

    /**
     * Update Parking Lot for Supervisor
     * @param dto
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.UPDATE_PARKING_LOT_FOR_SUPERVISOR, method = RequestMethod.PUT)
    public ResponseDTO updateParkingLotForSupervisor(@RequestBody @Valid ParkingLotDTO dto,
                                        @PathVariable Integer parkingLotId){
        return parkingLotService.updateParkingLot(dto, parkingLotId, null);
    }

    /**
     * Update Parking Lot for Admin
     * @param dto
     * @param parkingLotId
     * @param adminId
     * @return
     */
    @RequestMapping(value = Const.UPDATE_PARKING_LOT_FOR_ADMIN, method = RequestMethod.PUT)
    public ResponseDTO updateParkingLotForAdmin(@RequestBody @Valid ParkingLotDTO dto,
                                        @PathVariable Integer parkingLotId,
                                        @RequestParam (value = "adminId", required = true) Integer adminId){
        return parkingLotService.updateParkingLot(dto, parkingLotId, adminId);
    }

    /**
     * Get All Slot Of Parking Lot
     * @param parkingLotId
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_SLOT_OF_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getAllSlotOfParkingLot(@RequestParam (value = "parkingLotId", required = true) Integer parkingLotId){
        return parkingLotService.getAllSlotOfParkingLot(parkingLotId);
    }
}
