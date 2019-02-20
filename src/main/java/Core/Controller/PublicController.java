package Core.Controller;

import Core.Constant.Const;
import Core.DTO.ResponseDTO;
import Core.Service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Send Email
     * @return
     */
    @RequestMapping(value = Const.SEND_EMAIL, method = RequestMethod.PUT)
    public ResponseDTO sendEmail(@RequestParam(value = "email", required = true) String email,
                                 @RequestParam(value = "token", required = true) String token){
        return publicService.sendEmail(email,token);
    }
}
