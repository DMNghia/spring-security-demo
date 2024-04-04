package com.nghia.springsecuritydemo.service;

import com.nghia.springsecuritydemo.dto.DrugDto;

import java.util.List;

public interface DrugService {
    List<DrugDto> getAllDrugs(int page, int pageSize);
}
