package Core.Service;

import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.ParkingLot;

public interface SupervisionService {
    ResponseDTO assignParkingLotForSupervisor(Integer parkingLotId, Integer supervisorId);

    ResponseDTO deassignParkingLotForSupervisor(Integer parkingLotId, Integer supervisorId);
}
