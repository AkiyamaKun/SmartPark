package Core.Service;

import Core.DTO.ResponseDTO;
import Core.DTO.TransactionDTO;

public interface TransactionService {

    ResponseDTO getTransaction(Integer id);

    ResponseDTO getListTransaction();

    ResponseDTO saveTransaction(TransactionDTO transactionDTO);

    ResponseDTO checkPayment(Integer parkingLotId, String nonce);
}
