package Core.Service;

import Core.DTO.ResponseDTO;

public interface SupervisionService {
    ResponseDTO assignParkingLotForSupervisor(Integer parkingLotId, Integer supervisorId);
}
