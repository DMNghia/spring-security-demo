package com.nghia.springsecuritydemo.controller;

import com.nghia.springsecuritydemo.constant.StatusResponse;
import com.nghia.springsecuritydemo.dto.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drug")
public class DrugController {

    @GetMapping("")
    public ResponseEntity<?> getAllDrugs() {
        return new ResponseEntity<>(BaseResponse.builder()
                .status(StatusResponse.SUCCESS)
                .message("Thanh Cong")
                .build(), HttpStatus.OK);
    }
}
