package Core.Service.Impl;

import Core.Constant.Const;
import Core.DTO.PaymentDTO;
import Core.DTO.ResponseDTO;
import Core.Entity.Payment;
import Core.Repository.PaymentRepository;
import Core.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Function Convert DTO From Entity
     *
     * @param dto
     * @param entity
     */
    public void convertDTOFromEntity(PaymentDTO dto, Payment entity) {
        dto.setPaymentId(entity.getPaymentId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setBooking(entity.getBookingId());
        dto.setTypeOfMoney(entity.getTypeOfMoney());
        dto.setPaymentStatus(entity.getPaymentStatus());
    }

    @Override
    public ResponseDTO getPayment(Integer paymentId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            Payment payment = paymentRepository.findByPaymentId(paymentId);
            PaymentDTO dto = new PaymentDTO();
            if (payment != null) {
                convertDTOFromEntity(dto, payment);
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_PAYMENT_SUCCESS);
                responseDTO.setObjectResponse(dto);
            } else {
                responseDTO.setMessage(Const.PAYMENT_IS_NOT_EXISTED);
            }
        } catch (Exception e) {
            responseDTO.setMessage("Get payment is exception: " + e.getMessage());
        }
        return responseDTO;
    }

    @Override
    public ResponseDTO getListPayment() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(false);
        try {
            List<PaymentDTO> paymentDTOS = new ArrayList<>();
            List<Payment> payments = paymentRepository.findAll();
            for (Payment payment : payments) {
                PaymentDTO tmp = new PaymentDTO();
                convertDTOFromEntity(tmp, payment);
                paymentDTOS.add(tmp);
            }
            if (!paymentDTOS.isEmpty()) {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.GET_LIST_PAYMENT_SUCCESS);
                responseDTO.setObjectResponse(paymentDTOS);
            } else {
                responseDTO.setStatus(true);
                responseDTO.setMessage(Const.NOTHING_DATA_ON_SERVER);
            }
        } catch (Exception e) {
            responseDTO.setMessage(Const.GET_LIST_PAYMENT_FAIL);
        }
        return responseDTO;
    }
}
