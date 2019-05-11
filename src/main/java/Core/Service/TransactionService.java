package Core.Service;

import Core.DTO.ResponseDTO;

public interface TransactionService {

    ResponseDTO getTransaction(Integer id);

    ResponseDTO getListTransaction();
}
