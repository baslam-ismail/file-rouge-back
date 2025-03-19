package com.malsi.back_malsi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malsi.back_malsi.dto.DemandDto;
import com.malsi.back_malsi.model.Demand;
import com.malsi.back_malsi.service.DemandService;

@RestController
@RequestMapping("/api/demands")
public class DemandController {
    @Autowired
    private DemandService demandService;

    @PostMapping("/create")
    public ResponseEntity<DemandDto> createDemand(@RequestBody Demand demand) {
        DemandDto createdDemand = demandService.createDemand(demand);

        return new ResponseEntity<DemandDto>(createdDemand, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandDto> getDemandById(@PathVariable Integer id) {
        DemandDto demand = demandService.getDemandById(id);
        if (demand == null) {
            return new ResponseEntity<DemandDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DemandDto>(demand, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<DemandDto>> getDemands() {
        List<DemandDto> demands = demandService.getDemands();

        return new ResponseEntity<List<DemandDto>>(demands, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<DemandDto>> getClientDemands(@PathVariable Integer clientId) {
        List<DemandDto> demands = demandService.getClientDemands(clientId);
        if (demands == null) {
            return new ResponseEntity<List<DemandDto>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<DemandDto>>(demands, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<DemandDto>> getCategoryDemands(@PathVariable Integer categoryId) {
        List<DemandDto> demands = demandService.getDemandsByCategory(categoryId);
        if (demands == null) {
            return new ResponseEntity<List<DemandDto>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<DemandDto>>(demands, HttpStatus.OK);
    }

    @PutMapping("/update/status")
    public ResponseEntity<DemandDto> updateDemandStatus(@RequestBody Demand demand) {
        DemandDto updatedDemand = demandService.updateDemandStatus(demand.getId(), demand.getStatus());
        if (updatedDemand == null) {
            return new ResponseEntity<DemandDto>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<DemandDto>(updatedDemand, HttpStatus.OK);
    }
}
