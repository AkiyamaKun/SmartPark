package Core.Service;

import Core.DTO.OwnerDTO;
import Core.DTO.ResponseDTO;

public interface OwnerService {
    ResponseDTO getOwner(Integer ownerId);
    ResponseDTO getAllOwner();
    ResponseDTO createOwner(OwnerDTO dto);
    ResponseDTO updateOwner(Integer ownerId, OwnerDTO dto);
    ResponseDTO deactiveOwner(Integer ownerId);
    ResponseDTO activeOwner(Integer ownerId);
    ResponseDTO searchOwnerByName(String name);
}
