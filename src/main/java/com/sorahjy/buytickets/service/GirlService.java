package com.sorahjy.buytickets.service;

//事务管理

import com.sorahjy.buytickets.domain.Girl;
import com.sorahjy.buytickets.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girlA=new Girl();
        girlA.setAge("17");
        girlA.setUsername("1717");
        girlRepository.save(girlA);

        Girl girlB=new Girl();
        girlB.setAge("18");
        girlB.setUsername("1919");
        girlRepository.save(girlB);
    }

    public Girl findOne(Integer id){
        return girlRepository.findById(id).get();
    }

}
