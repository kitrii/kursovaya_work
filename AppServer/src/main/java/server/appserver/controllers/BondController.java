package server.appserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.appserver.entity.BondEntity;
import server.appserver.entity.BondsEntity;
import server.appserver.mapper.BondMapper;
import server.appserver.models.Bond;
import server.appserver.services.BondService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bonds")
public class BondController {

    private final BondService bondService;

    @Autowired
    public BondController(BondService bondService) {
        this.bondService = bondService;
    }


    /** Получить информацию о всех облигациях**/
    @GetMapping("/all")
    private ResponseEntity<List<BondEntity>> getBonds() {
        List<BondEntity> DbBonds = bondService.getAllBonds();
        return new ResponseEntity<>(DbBonds, HttpStatus.OK);
    }

    /** Добавление облигации в БД*/
    @PostMapping("/add")
    private ResponseEntity<?> addBond(@RequestBody BondsEntity bond) {
        Map<String, Object> map = new HashMap<>();
        bondService.addBondInfo(bond);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}