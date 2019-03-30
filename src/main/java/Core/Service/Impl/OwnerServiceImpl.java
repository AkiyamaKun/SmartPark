package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.OwnerDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Owner;
import Core.Repository.OwnerRepository;
import Core.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;

    /**
     * Function Convert DTO From Entity
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(OwnerDTO dto, Owner entity){
        dto.setOwnerId(entity.getOwnerId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setSex(entity.getSex());
        dto.setYearOfBirth(entity.getYearOfBirth());
        dto.setActive(entity.isActive());
    }

    /**
     * Get Owner by Id
     * @param ownerId
     * @return
     */
    @Override
    public ResponseDTO getOwner(Integer ownerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Owner owner = ownerRepository.findByOwnerId(ownerId);
            OwnerDTO dto = new OwnerDTO();
            if(owner!= null){
                convertDTOFromEntity(dto, owner);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_OWNER_SUCCESS);
                responseDTO.setObjectResponse(dto);
            }else{
                responseDTO.setMessage(Const.OWNER_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage("Get Owner is exception: " + e.getMessage());
        }
        return responseDTO;
    }

    /**
     * Get All Owners
     * @return
     */
    @Override
    public ResponseDTO getAllOwner() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            List<OwnerDTO> ownerDTOS = new ArrayList<>();
            List<Owner> owners = ownerRepository.findAll();
            for(Owner owner: owners){
                OwnerDTO tmp = new OwnerDTO();
                convertDTOFromEntity(tmp, owner);
                ownerDTOS.add(tmp);
            }
            if(!ownerDTOS.isEmpty()){
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_LIST_OWNER_SUCCESS);
                responseDTO.setObjectResponse(ownerDTOS);
            }else{
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.GET_LIST_OWNER_FAIL);
        }

        return responseDTO;
    }

    /**
     * Create new Owner
     * @param dto
     * @return
     */
    @Override
    public ResponseDTO createOwner(OwnerDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            OwnerDTO responseOwnerDTO = new OwnerDTO();
            Date date = new Date();
            Owner owner = new Owner();
            owner.setFirstName(dto.getFirstName());
            owner.setLastName(dto.getLastName());
            owner.setAddress(dto.getAddress());
            owner.setSex(dto.getSex());
            owner.setPhoneNumber(dto.getPhoneNumber());
            owner.setYearOfBirth(dto.getYearOfBirth());
            owner.setCreatedDate(date);
            owner.setActive(true);
            ownerRepository.save(owner);
            convertDTOFromEntity(responseOwnerDTO, owner);
            responseDTO.setStatus(true);
            responseDTO.setMessage(Const.CREATE_OWNER_SUCCESS);
            responseDTO.setObjectResponse(responseOwnerDTO);
        }catch (Exception e){
            responseDTO.setMessage(Const.CREATE_OWNER_FAIL);
        }
        return responseDTO;
    }

    /**
     * Update Owner
     * @param ownerId
     * @param dto
     * @return
     */
    @Override
    public ResponseDTO updateOwner(Integer ownerId, OwnerDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Owner owner = ownerRepository.findByOwnerId(ownerId);
            if(owner != null){
                owner.setFirstName(dto.getFirstName());
                owner.setLastName(dto.getLastName());
                owner.setAddress(dto.getAddress());
                owner.setSex(dto.getSex());
                owner.setPhoneNumber(dto.getPhoneNumber());
                owner.setYearOfBirth(dto.getYearOfBirth());
                ownerRepository.save(owner);
                OwnerDTO responseOwnerDTO = new OwnerDTO();
                convertDTOFromEntity(responseOwnerDTO, owner);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.UPDATE_OWNER_SUCCESS);
                responseDTO.setObjectResponse(responseOwnerDTO);
            }else{
                responseDTO.setMessage(Const.OWNER_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.UPDATE_OWNER_FAIL);
        }
        return responseDTO;
    }

    /**
     * Deactive Owner
     * @param ownerId
     * @return
     */
    @Override
    public ResponseDTO deactiveOwner(Integer ownerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Owner owner = ownerRepository.findByOwnerId(ownerId);
            if(owner != null){
                owner.setActive(false);
                ownerRepository.save(owner);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.DEACTIVE_OWNER_SUCCESS);
            }else{
                responseDTO.setMessage(Const.OWNER_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.DEACTIVE_OWNER_FAIL);
        }
        return responseDTO;
    }

    /**
     * Active Owner
     * @param ownerId
     * @return
     */
    @Override
    public ResponseDTO activeOwner(Integer ownerId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            Owner owner = ownerRepository.findByOwnerId(ownerId);
            if(owner != null){
                owner.setActive(true);
                ownerRepository.save(owner);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.ACTIVE_OWNER_SUCCESS);
            }else{
                responseDTO.setMessage(Const.OWNER_IS_NOT_EXISTED);
            }
        }catch (Exception e){
            responseDTO.setMessage(Const.ACTIVE_OWNER_FAIL);
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO searchOwnerByName(String searchValue){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try{
            List<Owner> owners = ownerRepository.searchOwnerByName(searchValue);
           if(!owners.isEmpty()) {
               responseDTO.setStatus(true);
               responseDTO.setObjectResponse(owners);
               responseDTO.setMessage(Const.SEARCH_OWNER_SUCCESS);
           }else{
               responseDTO.setStatus(true);
               responseDTO.setMessage(Const.SEARCH_OWNER_FIND_NOT_FOUND);
           }
        }catch (Exception e){
            responseDTO.setMessage("Search Owner Exception: " + e.getLocalizedMessage());
        }
        return responseDTO;
    }
}
