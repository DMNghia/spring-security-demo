package com.nghia.springsecuritydemo.service.implement;

import com.nghia.springsecuritydemo.dto.DrugDto;
import com.nghia.springsecuritydemo.entity.Drug;
import com.nghia.springsecuritydemo.respository.DrugRepository;
import com.nghia.springsecuritydemo.service.DrugService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugServiceIml implements DrugService {

    private final DrugRepository drugRepository;

    public DrugServiceIml(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @Override
    public List<DrugDto> getAllDrugs(int page, int pageSize) {
        Page<Drug> drugs = drugRepository.findAll(PageRequest.of(page, pageSize));
        return drugs.stream().map(record -> DrugDto.builder()
                .name(record.getName())
                .description(record.getDescription())
                .price(record.getPrice())
                .build()).toList();
    }
}
