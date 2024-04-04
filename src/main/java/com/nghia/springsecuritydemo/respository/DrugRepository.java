package com.nghia.springsecuritydemo.respository;

import com.nghia.springsecuritydemo.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Integer> {
    List<Drug> getAllBy(Pageable pageable);
}
