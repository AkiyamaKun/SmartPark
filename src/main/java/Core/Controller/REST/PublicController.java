package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.ParkingSlotDTO;
import Core.DTO.ResponseDTO;
import Core.Service.BookingService;
import Core.Service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Public Controller
 *
 * Author: DangNHH - 17/02/2019
 */
@RestController
@RequestMapping(value = Const.PUBLIC)
public class PublicController {

    @Autowired
    PublicService publicService;

    @Autowired
    BookingService bookingService;

    /**
     * Update Status Parking Slot From Deep Learning
     * @param parkingLotId
     * @param listParkingSlot
     * @return
     */
    @RequestMapping(value = Const.UPDATE_STATUS_SLOT, method = RequestMethod.PUT)
    public ResponseDTO updateStatusSlot(@RequestParam(value = "parkingLotId", required = true) Integer parkingLotId,
                                        @RequestBody @Valid List<ParkingSlotDTO> listParkingSlot){
        return publicService.updateStatusSlot(parkingLotId, listParkingSlot);
    }

    @RequestMapping(value = Const.UPLOAD_IMAGE_FOR_PARKING_LOT, method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDTO uploadImageForParkingLot(@RequestParam(value = "image", required = true) MultipartFile image,
                                                @PathVariable Integer parkingLotId){
        return publicService.uploadImageForParkingLot(image, parkingLotId);
    }

    /**
     * Check In
     *
     * @param bookingId
     * @param token
     * @return
     */
    @RequestMapping(value = Const.BOOKING_CHECK_IN, method = RequestMethod.PUT)
    public ResponseDTO checkIn(@RequestParam(value = "bookingId") Integer bookingId,
                               @RequestParam(value = "token") String token) {
        return bookingService.checkIn(bookingId, token);
    }

    /**
     * Check Out
     *
     * @param bookingId
     * @param token
     * @return
     */
    @RequestMapping(value = Const.BOOKING_CHECK_OUT, method = RequestMethod.PUT)
    public ResponseDTO checkOut(@RequestParam(value = "bookingId") Integer bookingId,
                                @RequestParam(value = "token") String token) {
        return bookingService.checkOut(bookingId, token);
    }
}
