package server.appserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.appserver.calculation.Calculation;
import server.appserver.entity.BondEntity;

import server.appserver.entity.BondInPortfolioEntity;
import server.appserver.entity.PortfolioEntity;
import server.appserver.services.PortfolioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/portfolio", produces = "application/json")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {

        this.portfolioService = portfolioService;
    }
    /** Добавить облигацию в портфель по owner id и bond_id **/
    @ PostMapping("/add")
    private ResponseEntity<?> addBond(@RequestBody PortfolioEntity portfolio) {
        Map<String, Object> map = new HashMap<>();
        portfolioService.addBondInPortfolio(portfolio);
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    /** Получить портфель облигаций по owner_id**/
    @GetMapping("/owner")
    private ResponseEntity<List<BondInPortfolioEntity>> getBondsByOwnerId(@RequestParam(name = "ownerId") String ownerId) {
        List<BondInPortfolioEntity> DbBonds = portfolioService.getBondsByOwnerId(ownerId);
        return new ResponseEntity<>(DbBonds, HttpStatus.OK);
    }

    /**Рассчитать дюрацию всех облигаций для пользователя по ownerId*/
    @GetMapping("/calculateDuration")
    private ResponseEntity<Float> calculateDurationByOwnerId(@RequestParam(name = "ownerId") String ownerId) {
        List<BondInPortfolioEntity> portfolioBonds = portfolioService.getBondsByOwnerId(ownerId);
        Float duration = Calculation.calculateDuration(portfolioBonds);
        return new ResponseEntity<>(duration, HttpStatus.OK);
    }

}