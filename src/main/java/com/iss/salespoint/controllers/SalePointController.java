package com.iss.salespoint.controllers;

import com.iss.salespoint.entities.SalePoint;
import com.iss.salespoint.repos.SalePointRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SalePointController {

    private SalePointRepo salePointRepo;

    public SalePointController(SalePointRepo salePointRepo) {
        this.salePointRepo = salePointRepo;
    }

    @GetMapping("/sale-points")
    public ResponseEntity<List<SalePoint>> getAllSalePoints() {
        try {
            List<SalePoint> salePoints = new ArrayList<>();

            salePointRepo.findAll().forEach(salePoints::add);

            if (salePoints.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(salePoints, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/sale-point/{id}")
    public ResponseEntity<SalePoint> getSalePointById(@PathVariable Long id) {
        Optional<SalePoint> salePointData = salePointRepo.findById(id);

        if (salePointData.isPresent()) {
            return new ResponseEntity<>(salePointData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sale-points")
    public ResponseEntity<Long> createSalePoint(@RequestBody SalePoint salePoint) {
        try {
            salePointRepo.save(new SalePoint(salePoint.getName(), salePoint.getAddress(),
                                                salePoint.getPhone(), salePoint.getINN(),
                                                salePoint.getWorkingRegime(), salePoint.getAmountPerExit(),
                                                salePoint.getIsWorking()));
            return new ResponseEntity<>(salePoint.getId(), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sale-points/{salePointId}")
    public ResponseEntity<HttpStatus> deleteSalePoint(@PathVariable("salePointId") Long salePointId) {
        try {
            salePointRepo.deleteById(salePointId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/sale-points/update/{salePointId}")
    public ResponseEntity<Long> patchSalePoint(@PathVariable("salePointId") Long salePointId, @RequestBody SalePoint patch) {
        SalePoint point = salePointRepo.findById(salePointId).get();

        if(patch.getName() != null) {
            point.setName(patch.getName());
        }
        if(patch.getAddress() != null) {
            point.setAddress(patch.getAddress());
        }
        if(patch.getPhone() != null) {
            point.setPhone(patch.getPhone());
        }
        if(patch.getINN() != null) {
            point.setINN(patch.getINN());
        }
        if(patch.getWorkingRegime() != null) {
            point.setWorkingRegime(patch.getWorkingRegime());
        }
        if(patch.getAmountPerExit() != null) {
            point.setAmountPerExit(patch.getAmountPerExit());
        }
        if(patch.getIsWorking() != null) {
            point.setIsWorking(patch.getIsWorking());
        }
        salePointRepo.save(point);
        return new ResponseEntity<>(point.getId(), HttpStatus.OK);
    }
}
