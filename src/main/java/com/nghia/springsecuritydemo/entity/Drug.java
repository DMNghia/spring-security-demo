package com.nghia.springsecuritydemo.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "DRUG")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Drug extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;
    @Column(columnDefinition = "text")
    private String description;
}
