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
    OwnerService ownerService;

    /**
     * Get Account
     * @param id
     * @return
     */
    @RequestMapping(value = Const.GET_OWNER, method = RequestMethod.GET)
    public ResponseDTO getOwner(@PathVariable Integer id,
                                  HttpServletRequest request){
        return ownerService.getOwner(id);
    }

    /**
     * Get All Owner
     * @return
     */
    @RequestMapping(value = Const.GET_ALL_OWNER, method = RequestMethod.GET)
    public ResponseDTO getAllOwner(HttpServletRequest request){
        return ownerService.getAllOwner();
    }

    /**
     * Create Owner
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.CREATE_OWNER, method = RequestMethod.POST)
    public ResponseDTO createOwner(@RequestBody @Valid OwnerDTO dto,
                                  HttpServletRequest request){
        return ownerService.createOwner(dto);
    }

    /**
     * Update Owner
     * @param dto
     * @return
     */
    @RequestMapping(value = Const.UPDATE_OWNER, method = RequestMethod.PUT)
    public ResponseDTO updateOwner(@PathVariable Integer id,
                                   @RequestBody @Valid OwnerDTO dto,
                                   HttpServletRequest request){
        return ownerService.updateOwner(id, dto);
    }

    /**
     * Deactive Owner
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = Const.DEACTIVE_OWNER, method = RequestMethod.PUT)
    public ResponseDTO updateOwner(@PathVariable Integer id,
                                   HttpServletRequest request){
        return ownerService.deactiveOwner(id);
    }
}
