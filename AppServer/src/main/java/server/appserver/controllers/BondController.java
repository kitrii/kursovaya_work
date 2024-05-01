package server.appserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.appserver.entity.BondEntity;
import server.appserver.entity.CouponEntity;
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
    private ResponseEntity<?> addBond(@RequestBody BondEntity bond) {
        Map<String, Object> map = new HashMap<>();
        bondService.addBond(bond);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    /** Удаление облигации из БД*/
    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteBondByBondId(@RequestParam(name = "bondId") String bondId) {
        Map<String, Object> map = new HashMap<>();
        bondService.deleteBondByBondId(bondId);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /**Обновить информауию об облигации*/
    @PutMapping("/edit")
    private ResponseEntity<?> editBondInfo(@RequestBody BondEntity bond) {
        Map<String, Object> map = new HashMap<>();
        bondService.editBond(bond);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** Добавление купона к облигации в БД*/
    @PostMapping("/addcoupon")
    private ResponseEntity<?> addCouponToBond(@RequestBody CouponEntity coupon) {
        Map<String, Object> map = new HashMap<>();
        bondService.addCouponToBond(coupon);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** Добавление купона к облигации в БД*/
    @PutMapping("/editcoupon")
    private ResponseEntity<?> editCouponToBond(@RequestBody CouponEntity coupon) {
        Map<String, Object> map = new HashMap<>();
        bondService.editCouponToBond(coupon);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}