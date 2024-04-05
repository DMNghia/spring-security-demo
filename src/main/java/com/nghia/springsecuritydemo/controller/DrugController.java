package com.nghia.springsecuritydemo.controller;

import com.nghia.springsecuritydemo.constant.StatusResponse;
import com.nghia.springsecuritydemo.dto.DrugDto;
import com.nghia.springsecuritydemo.dto.request.GetDrugRequest;
import com.nghia.springsecuritydemo.dto.response.BaseResponse;
import com.nghia.springsecuritydemo.service.DrugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drug")
public class DrugController {

    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllDrugs(@RequestBody GetDrugRequest request) {
        List<DrugDto> result = drugService.getAllDrugs(request.getPageNo(), request.getPageSize());
        return new ResponseEntity<>(BaseResponse.builder()
                .status(StatusResponse.SUCCESS)
                .message("Thanh Cong")
                .data(result)
                .build(), HttpStatus.OK);
    }
}
