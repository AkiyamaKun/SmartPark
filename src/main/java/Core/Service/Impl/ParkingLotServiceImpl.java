package Core.Service.Impl;

import Core.DTO.ParkingLotDTO;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.OwnershipRepository;
import Core.Repository.ParkingLotRepository;
import Core.Service.ParkingLotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private OwnershipRepository ownershipRepository;


    @Override
    public List<ParkingLotDTO> getListParkingLot() {
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        List<ParkingLotDTO> parkingLotDTOS = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        parkingLots.stream().forEach(e -> {
//            ParkingLotDTO dto = modelMapper.map(e, ParkingLotDTO.class);
//            parkingLotDTOS.add(dto);
//        });

        for(ParkingLot parkingLot: parkingLots){
            ParkingLotDTO dto = new ParkingLotDTO();
            dto.setDisplayName(parkingLot.getDisplayName());
            dto.setTotalSlot(parkingLot.getTotalSlot());
            dto.setOwnedBy(ownershipRepository.findByParkingLot(parkingLot).getSupervisor().getAccountId());
            parkingLotDTOS.add(dto);
        }
        return parkingLotDTOS;
    }
}
