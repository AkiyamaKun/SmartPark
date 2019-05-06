package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.Entity.Account;
import Core.Entity.ParkingLot;
import Core.Entity.Supervision;
import Core.Repository.AccountRepository;
import Core.Repository.ParkingLotRepository;
import Core.Repository.SupervisionRepository;
import Core.Service.SupervisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisionServiceImpl implements SupervisionService {

    @Autowired
    SupervisionRepository supervisionRepository;

    @Autowired
    ParkingLotRepository parkingLotRepository;

    @Autowired
    AccountRepository accountRepository;

    /**
     * Assign Parking Lot for Supervisor
     *
     * @param parkingLotId
     * @param supervisorId
     * @return
     */
    @Override
    public ResponseDTO assignParkingLotForSupervisor(Integer parkingLotId, Integer supervisorId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            Account supervisor = accountRepository.findByAccountId(supervisorId);
            if (parkingLot != null && supervisor != null) {
                //Check Existed
                List<Supervision> supervisions = supervisionRepository.findByParkingLot(parkingLot);
                boolean existed = false;
                if (!supervisions.isEmpty()) {
                    for (Supervision supervision : supervisions) {
                        if (supervision.getSupervisor().getAccountId() == supervisorId) {
                            existed = true;
                            break;
                        }
                    }
                }
                if (!existed) {
                    //Not existed -> Create new Supervision
                    Supervision supervision = new Supervision();
                    supervision.setParkingLot(parkingLot);
                    supervision.setSupervisor(supervisor);
                    supervisionRepository.save(supervision);

                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.ASSIGN_PARKING_LOT_FOR_SUPERVISOR_SUCCESS);
                    responseDTO.setObjectResponse(supervision);
                }
            } else {
                responseDTO.setMessage(Const.ASSIGN_PARKING_LOT_FOR_SUPERVISOR_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Assign Parking Lot for Supervisor Error: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO deassignParkingLotForSupervisor(Integer parkingLotId, Integer supervisorId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            Account supervisor = accountRepository.findByAccountId(supervisorId);
            Supervision supervision = supervisionRepository.findByParkingLotAndSupervisor(parkingLot, supervisor);
            if (supervision != null) {
                supervision.setParkingLot(null);
                supervision.setSupervisor(null);
                supervisionRepository.save(supervision);
                supervisionRepository.delete(supervision);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.DEASSIGN_PARKING_LOT_FOR_SUPERVISOR_SUCCESS);
            } else {
                responseDTO.setMessage(Const.DEASSIGN_PARKING_LOT_FOR_SUPERVISOR_FAIL);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Deassign Parking Lot for Supervisor Error: " + e.getMessage());
        }
        return responseDTO;
    }
}
