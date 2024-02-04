package server.appserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.appserver.entity.BondEntity;
import server.appserver.entity.BondsEntity;
import server.appserver.mapper.BondMapper;
import server.appserver.models.Bond;
import server.appserver.operations.BondsTableOperations;
import server.appserver.services.BondService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/portfolio")
public class PortfolioController {

    private final BondsTableOperations BTO = new BondsTableOperations();


    /** Получить портфель облигаций по owner_id**/
    @GetMapping("/owner")
    private ResponseEntity<List<Bond>> getBondsByOwnerId(@RequestParam(name = "ownerId") int ownerId) {
        List<BondsEntity> DbBonds = BTO.getBondsByOwnerId(ownerId);
        List<Bond> bonds = BondMapper.BondEntityToBondMapper(DbBonds);
        return new ResponseEntity<>(bonds, HttpStatus.OK);
    }


}