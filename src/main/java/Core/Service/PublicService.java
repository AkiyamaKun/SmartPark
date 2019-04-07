package Core.Service;

import Core.DTO.ParkingSlotDTO;
import Core.DTO.ResponseDTO;

import java.util.List;

public interface PublicService {
    ResponseDTO sendEmail(String email, String token, Integer roleAccount);
    ResponseDTO updateStatusSlot(Integer parkingLotId, List<ParkingSlotDTO> listParkingSlot);
}
