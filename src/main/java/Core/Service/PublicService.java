package Core.Service;

import Core.DTO.ResponseDTO;

public interface PublicService {
    ResponseDTO sendEmail(String email, String token);
}
