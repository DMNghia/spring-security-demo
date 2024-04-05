package com.nghia.springsecuritydemo.respository;

import com.nghia.springsecuritydemo.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer> {
}
