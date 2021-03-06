package Core.Service;

import Core.DTO.ResponseDTO;
import Core.DTO.TransactionDTO;

public interface TransactionService {

    ResponseDTO getTransaction(Integer id);

    ResponseDTO getRevenue();

    ResponseDTO saveTransaction(TransactionDTO transactionDTO);

    ResponseDTO checkPayment(Integer parkingLotId, String nonce);

    ResponseDTO generateTokenClient();

    ResponseDTO getTransactionByAccountId(Integer accountId, Integer amount);

    ResponseDTO getTransactionByBookingId(Integer bookingId);

    ResponseDTO refundForDriver(Integer parkingLotId);
}
