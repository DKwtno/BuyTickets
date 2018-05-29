package com.sorahjy.buytickets.repository;

import com.sorahjy.buytickets.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GirlRepository extends JpaRepository<Girl,Integer> {
    public List<Girl> findByAge(String age);

}
