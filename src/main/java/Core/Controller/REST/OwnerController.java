package Core.Controller.REST;

import Core.Constant.Const;
import Core.DTO.OwnerDTO;
import Core.DTO.ResponseDTO;
import Core.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = Const.OWNER)
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    /**
     * Update Owner
     * @param ownerDTO
     * @return
     */
    @RequestMapping(value = Const.UPDATE_OWNER, method = RequestMethod.PUT)
    public ResponseDTO updateAccount(@RequestBody @Valid OwnerDTO ownerDTO,
                                     @PathVariable Integer id){
        ResponseDTO responseDTO = ownerService.updateOwner(id, ownerDTO);
        return responseDTO;
    }

    /**
     * Deactive Owner
     * @param id
     * @return
     */
    @RequestMapping(value = Const.DEACTIVE_OWNER, method = RequestMethod.PUT)
    public ResponseDTO deactiveAccount(@PathVariable Integer id){
        ResponseDTO responseDTO = ownerService.deactiveOwner(id);
        return responseDTO;
    }
}
