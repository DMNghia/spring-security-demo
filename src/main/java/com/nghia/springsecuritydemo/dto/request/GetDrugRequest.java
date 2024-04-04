package com.nghia.springsecuritydemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetDrugRequest {
    private int pageSize;
    private int pageNo;
}
