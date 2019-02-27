package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.Entity.ParkingSlot;
import Core.Repository.ParkingSlotRepository;
import Core.Service.ParkingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotServiceImpl implements ParkingSlotService {

    @Autowired
    ParkingSlotRepository parkingSlotRepository;

    /**
     * Get Parking Slot
     * @param id
     * @return
     */
    @Override
    public ResponseDTO getParkingSlot(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            ParkingSlot parkingSlot = parkingSlotRepository.findBySlotId(id);
            if(parkingSlot != null){
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_PARKING_SLOT_SUCCESS);
                responseDTO.setObjectResponse(parkingSlot);
            }else{
                responseDTO.setMessage(Const.PARKING_SLOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
           responseDTO.setMessage("Get Parking Slot Exception : " + e.getMessage());
        }
        return responseDTO;
    }
}
