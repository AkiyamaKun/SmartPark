package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.Service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = Const.PARKING_SLOT)
public class ParkingSlotController {

    @Autowired
    ParkingSlotService parkingSlotService;

    /**
     * Get Parking Slot by Id
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_PARKING_SLOT, method = RequestMethod.GET)
    public ResponseDTO getParkingSlot(@PathVariable Integer id){
        return parkingSlotService.getParkingSlot(id);
    }
}
