package Core.Service;

import Core.DTO.ParkingLotDTO;
import Core.DTO.ParkingLotUpdateDTO;
import Core.DTO.ResponseDTO;

public interface ParkingLotService {
    //Common
    ResponseDTO getParkingLot(Integer id);
    ResponseDTO getAllSlotOfParkingLot(Integer parkingLotId);
    ResponseDTO updateParkingLot(ParkingLotUpdateDTO dto, Integer accountId);

    //For Admin Account
    ResponseDTO getAllParkingLotForAdmin();
    ResponseDTO createParkingLot(ParkingLotDTO dto, Integer ownerId, Integer adminId);

    ResponseDTO deactiveParkingLot(Integer parkingLotId);
    //For Supervisor Account
    ResponseDTO getListParkingLotManagedBySupervisor(Integer supervisorId);

    //For Driver Account
    ResponseDTO getListParkingLotActiveForAndroid();
}
