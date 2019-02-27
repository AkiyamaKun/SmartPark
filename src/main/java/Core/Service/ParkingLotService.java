package Core.Service;

import Core.DTO.ParkingLotDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.ParkingLot;

import java.util.List;

public interface ParkingLotService {
    ResponseDTO getParkingLot(Integer id);
    ResponseDTO getListParkingLot();
    ResponseDTO createParkingLot(ParkingLotDTO dto, Integer ownerId, Integer adminId);
    ResponseDTO updateParkingLot(ParkingLotDTO dto, Integer parkingLotId, Integer adminId);
    ResponseDTO getAllSlotOfParkingLot(Integer parkingLotId);
}
