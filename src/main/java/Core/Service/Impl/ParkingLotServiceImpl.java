package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.*;
import Core.Entity.*;
import Core.Repository.*;
import Core.Entity.Account;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.ParkingLotRepository;
import Core.Service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ParkingSlotStatusRepository parkingSlotStatusRepository;

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private SupervisionRepository supervisionRepository;

    /**
     * Conver Short Info Parking Lot From Entity
     * @param dto
     * @param entity
     */
    public void convertShortInfoParkingLotFromEntity(ShortInfoParkingLotDTO dto, ParkingLot entity){
        dto.setParkingLotId(entity.getParkingLotId());
        dto.setDisplayName(entity.getDisplayName());
        dto.setTotalSlot(entity.getTotalSlot());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setTimeOfOperation(entity.getTimeOfOperation());
        dto.setOwner(entity.getOwner());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setActive(entity.isActive());
        dto.setParklotImage(entity.getParklotImage());
    }

    /**
     * Convert
     * @param dto
     * @param entity
     */
    public void convertParkingSlotDTOFormEntity(ParkingSlotDTO dto, ParkingSlot entity){
        dto.setSlotId(entity.getSlotId());
        dto.setName(entity.getName());
        dto.setParkingLotId(entity.getParkingLot().getParkingLotId());
        dto.setStatus(entity.getParkingSlotStatus().getStatusName());
    }

    /**
     * Get Parking Lot by Id
     * @param id
     * @return
     */
    @Override
    public ResponseDTO getParkingLot(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(id);
            if(parkingLot != null){
                if(parkingLot.isActive()){
                    ShortInfoParkingLotDTO dto = new ShortInfoParkingLotDTO();
                    parkingLot.getEditedBy().setPassword(null);
                    parkingLot.getCreatedBy().setPassword(null);
                    convertShortInfoParkingLotFromEntity(dto, parkingLot);
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.GET_PARKING_LOT_SUCCESS);
                    responseDTO.setObjectResponse(dto);
                }else{
                    //parking lot is deactive
                    responseDTO.setMessage(Const.PARKING_LOT_IS_DEACTIVE);
                }

            }else{
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.GET_PARKING_LOT_FAIL);
        }
        return responseDTO;
    }

    /**
     * Get List Parking Lot for Admin
     * Get all parking lot (active / deactive)
     * @return
     */
    @Override
    public ResponseDTO getListParkingLotActiveForAndroid() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        if(!parkingLots.isEmpty()){
            List<ShortInfoParkingLotDTO> shortInfoParkingLotDTOS = new ArrayList<>();
            for(ParkingLot parkingLot: parkingLots){
                parkingLot.getCreatedBy().setPassword(null);
                parkingLot.getEditedBy().setPassword(null);
                ShortInfoParkingLotDTO tmp = new ShortInfoParkingLotDTO();
                convertShortInfoParkingLotFromEntity(tmp, parkingLot);
                shortInfoParkingLotDTOS.add(tmp);
            }
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.GET_LIST_PARKING_LOT_SUCCESS);
            responseDTO.setObjectResponse(shortInfoParkingLotDTOS);
        }else{
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
        }
        return responseDTO;
    }

    /**
     * Create Parking Lot
     * @param dto
     * @param adminId
     * @return
     */
    @Override
    public ResponseDTO createParkingLot(ParkingLotUpdateDTO dto, Integer adminId){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Owner owner = ownerRepository.findByOwnerId(dto.getOwnerId());
            Account adminAccount = accountRepository.findByAccountId(adminId);
            if(owner != null && adminAccount != null){
                if(dto != null){
                    //Create new record 'Parking Lot' on table Parking Lot
                    Date date = new Date();
                    ParkingLot parkingLot = new ParkingLot();
                    parkingLot.setDisplayName(dto.getDisplayName());
                    parkingLot.setAddress(dto.getAddress());
                    parkingLot.setLongitude(dto.getLongitude());
                    parkingLot.setLatitude(dto.getLatitude());
                    parkingLot.setPhoneNumber(dto.getPhoneNumber());
                    parkingLot.setTimeOfOperation(dto.getTimeOfOperation());
                    parkingLot.setTotalSlot(dto.getTotalSlot());
                    parkingLot.setCreatedBy(adminAccount);
                    parkingLot.setEditedBy(adminAccount);
                    parkingLot.setActive(true);
                    parkingLot.setLastEdited(date);
                    parkingLot.setCreatedDate(date);
                    parkingLot.setOwner(owner);
                    parkingLot.setParklotImage(dto.getParklotImage());
                    parkingLotRepository.save(parkingLot);

                    //Generate Slot for Parking Lot
                    //Status default 'Free'
                    ParkingSlotStatus status = parkingSlotStatusRepository.findByStatusName("Free");
                    if(status == null){
                        //If default status Free is not existed then create it
                        status = new ParkingSlotStatus("Free");
                        parkingSlotStatusRepository.save(status);
                    }
                    for(int i = 0; i <parkingLot.getTotalSlot(); i++){
                        String name = parkingLot.getDisplayName() + " " + (i + 1);
                        ParkingSlot parkingSlot = new ParkingSlot(name, status, parkingLot);
                        parkingSlotRepository.save(parkingSlot);
                    }

                    //Return
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.CREATE_PARKING_LOT_SUCCESS);
                    responseDTO.setObjectResponse(parkingLot);
                }else
                    responseDTO.setMessage(Const.LACK_OF_DATA);
            }else{
                responseDTO.setMessage(Const.CREATE_PARKING_LOT_FAIL);
            }
        }catch (Exception e){
            responseDTO.setMessage("Create Parking Lot Exception: " + e.getMessage());
        }

        return responseDTO;
    }

    /**
     * Update Parking Lot
     * @param dto
     * @return
     */
    @Override
    public ResponseDTO updateParkingLot(ParkingLotUpdateDTO dto, Integer accountId){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            if(dto != null){
                ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(dto.getParkingLotId());
                Account account = accountRepository.findByAccountId(accountId);
                Owner owner = ownerRepository.findByOwnerId(dto.getOwnerId());
                if(parkingLot != null){
                    if(account != null){
                        //Update Data For Supervisor
                        Date date = new Date();
                        parkingLot.setLastEdited(date);
                        parkingLot.setPhoneNumber(dto.getPhoneNumber());
                        parkingLot.setAddress(dto.getAddress());
                        parkingLot.setDisplayName(dto.getDisplayName());
                        parkingLot.setTimeOfOperation(dto.getTimeOfOperation());

                        if(account.getRole().getRoleId() == 1){
                            //Update Parking Lot for Admin
                            parkingLot.setEditedBy(account);
                            parkingLot.setLatitude(dto.getLatitude());
                            parkingLot.setLongitude(dto.getLongitude());
                            parkingLot.setActive(dto.isActive());
                            parkingLot.setOwner(owner);
                        }else{
                            parkingLot.setEditedBy(account);
                        }
                        parkingLot.setParklotImage(dto.getParklotImage());
                        //Excute Query Update Database
                        parkingLotRepository.save(parkingLot);
                        //Excute data before return Front-End
                        parkingLot.getCreatedBy().setPassword(null);
                        parkingLot.getEditedBy().setPassword(null);

                        responseDTO.setStatus(true);
                        responseDTO.setMessage(Const.UPDATE_PARKING_LOT_SUCCESS);
                        responseDTO.setObjectResponse(parkingLot);
                    }else{
                        responseDTO.setMessage(Const.UPDATE_PARKING_LOT_FAIL);
                    }
                }else{
                    responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
                }
            }else{
                responseDTO.setMessage(Const.LACK_OF_DATA);
            }
        }catch (Exception e){
            responseDTO.setMessage("Update Parking Lot Exception: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Deactive Parking Lot
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO deactiveParkingLot(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if(parkingLot != null){
                parkingLot.setActive(false);
                parkingLotRepository.save(parkingLot);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.PARKING_LOT_IS_DEACTIVE);
            }else{
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.PARKING_LOT_DEACTIVE_FAIL);
        }
        return responseDTO;
    }

    /**
     * Active Parking Lot
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO activeParkingLot(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if(parkingLot != null){
                parkingLot.setActive(true);
                parkingLotRepository.save(parkingLot);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.PARKING_LOT_IS_ACTIVE);
            }else{
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.PARKING_LOT_ACTIVE_FAIL);
        }
        return responseDTO;
    }

    /**
     * Get List Parking Lot Of Owner
     * @param ownerId
     * @return
     */
    @Override
    public ResponseDTO getListParkingLotOfOwner(Integer ownerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Owner owner = ownerRepository.findByOwnerId(ownerId);
            if(owner != null) {
                List<ParkingLot> parkingLots = parkingLotRepository.findByOwner(owner);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_LIST_PARKING_LOT_OF_OWNER_SUCCESS);
                responseDTO.setObjectResponse(parkingLots);
            }else{
                responseDTO.setMessage(Const.GET_LIST_PARKING_LOT_OF_OWNER_FAIL);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get List Parking Lot Of Onwer Exception: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getListParkingLotControlBySupervisor(Integer supervisorId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Account supervisor = accountRepository.findByAccountId(supervisorId);
            if(supervisor != null && supervisor.getRole().getRoleName().equals(Const.ROLE_SUPERVISOR)){
                List<Supervision> supervisions = supervisionRepository.findBySupervisor(supervisor);
                List<ParkingLot> parkingLots = new ArrayList<>();
                for (Supervision item: supervisions) {
                    parkingLots.add(item.getParkingLot());
                }
                responseDTO.setStatus(true);
                responseDTO.setObjectResponse(parkingLots);
                responseDTO.setMessage(Const.GET_LIST_PARKING_LOT_SUCCESS);
            }else{
                responseDTO.setMessage(Const.GET_LIST_PARKING_LOT_FAIL);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get List Parking Lot Control By Supervisor Error:  " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get List Supervisor Of Parking Lot
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO getListSupervisorOfParkingLot(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if(parkingLot != null){
                List<Supervision> supervisions = supervisionRepository.findByParkingLot(parkingLot);
                List<Account> accounts = new ArrayList<>();
                for (Supervision item: supervisions) {
                    accounts.add(item.getSupervisor());
                }
                responseDTO.setStatus(true);
                responseDTO.setObjectResponse(accounts);
                responseDTO.setMessage(Const.GET_LIST_SUPERVISOR_SUCCESS);
            }else{
                responseDTO.setMessage(Const.GET_LIST_SUPERVISOR_FAIL);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get List Supervisor Error:  " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get List Parking Lot Control By Supervisor
     * @param supervisorId
     * @return
     */
    @Override
    public ResponseDTO getListParkingLotManagedBySupervisor(Integer supervisorId) {
        return null;
    }

    /**
     * Get All Slot of Parking Lot
     * @param parkingLotId
     * @return
     */
    @Override
    public ResponseDTO getAllSlotOfParkingLot(Integer parkingLotId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            ParkingLot parkingLot = parkingLotRepository.findByParkingLotId(parkingLotId);
            if(parkingLot != null){
                List<ParkingSlot> parkingSlots = parkingSlotRepository.findByParkingLot(parkingLot);
                List<ParkingSlotDTO> parkingSlotDTOS = new ArrayList<>();
                if(!parkingSlots.isEmpty()){
                    for(ParkingSlot parkingSlot : parkingSlots){
                        parkingLot.getCreatedBy().setPassword(null);
                        parkingLot.getEditedBy().setPassword(null);
                        ParkingSlotDTO tmp = new ParkingSlotDTO();
                        convertParkingSlotDTOFormEntity(tmp, parkingSlot);
                        parkingSlotDTOS.add(tmp);
                    }
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.GET_ALL_SLOT_OF_PARKING_LOT_SUCCESS);
                    responseDTO.setObjectResponse(parkingSlotDTOS);
                }else{
                    responseDTO.setStatus(true);
                    responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
                }
            }else{
                responseDTO.setMessage(Const.PARKING_LOT_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get All Slot Exception: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get All Parking Lot For Admin
     * @return
     */
    @Override
    public ResponseDTO getAllParkingLotForAdmin() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        if(!parkingLots.isEmpty()){
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.GET_LIST_PARKING_LOT_SUCCESS);
            responseDTO.setObjectResponse(parkingLots);
        }else{
            responseDTO.setMessage(Const.GET_LIST_PARKING_LOT_FAIL);
        }
        return responseDTO;
    }
}
