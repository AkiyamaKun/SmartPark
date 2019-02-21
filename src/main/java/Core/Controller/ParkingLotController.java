package Core.Controller;

import Core.Constant.Const;
import Core.DTO.ParkingLotDTO;
import Core.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = Const.PARKINGLOT)
public class ParkingLotController {

    @Autowired
    private ParkingLotService parkingLotService;

    /**
     * Get list parking lots
     * @return
     */
    @RequestMapping(value = Const.LIST_PARKINGLOT, method = RequestMethod.GET)
    public List<ParkingLotDTO> getListParkingLot() {
        return parkingLotService.getListParkingLot();
    }
}
