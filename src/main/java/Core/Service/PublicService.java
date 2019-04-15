package Core.Service;

import Core.DTO.ParkingSlotDTO;
import Core.DTO.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicService {
    ResponseDTO sendEmail(String email, String token, Integer roleAccount, String nameAccount);
    ResponseDTO updateStatusSlot(Integer parkingLotId, List<ParkingSlotDTO> listParkingSlot);
    ResponseDTO uploadImageForParkingLot(MultipartFile file, Integer parkingLotId);
}
