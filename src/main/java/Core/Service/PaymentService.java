package Core.Service;

import Core.DTO.ResponseDTO;

public interface PaymentService {

    ResponseDTO getPayment(Integer paymentId);

    ResponseDTO getListPayment();
}
