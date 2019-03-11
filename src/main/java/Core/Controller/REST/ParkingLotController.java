package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.ParkingLotDTO;
import Core.DTO.ResponseDTO;
import Core.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @RequestMapping(value = Const.GET_ALL_PARKING_LOT, method = RequestMethod.GET)
    public ResponseDTO getListParkingLot() {
        return parkingLotService.getListParkingLotActiveForAndroid();
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
