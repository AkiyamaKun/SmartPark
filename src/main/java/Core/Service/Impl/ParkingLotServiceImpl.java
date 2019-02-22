package Core.Service.Impl;

import Core.DTO.ParkingLotDTO;
import Core.Entity.Account;
import Core.Entity.ParkingLot;
import Core.Repository.AccountRepository;
import Core.Repository.ParkingLotRepository;
import Core.Service.ParkingLotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<ParkingLotDTO> getListParkingLot() {
        List<ParkingLot> parkingLots = parkingLotRepository.findAll();
        List<ParkingLotDTO> parkingLotDTOS = new ArrayList<>();
        parkingLots.stream().forEach(e -> {
            Optional<Account> optionalAccounts = Optional.ofNullable(accountRepository.findByAccountId(e.getCreatedBy()));
            optionalAccounts.ifPresent(o -> {
                ModelMapper modelMapper = new ModelMapper();
                ParkingLotDTO dto = modelMapper.map(e, ParkingLotDTO.class);
                dto.setFirstName(o.getFirstName());
                dto.setLastName(o.getLastName());
                parkingLotDTOS.add(dto);
            });
        });
        return parkingLotDTOS;
    }
}
