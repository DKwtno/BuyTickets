package com.sorahjy.buytickets.controller;

import com.sorahjy.buytickets.aspect.HTTPAspect;
import com.sorahjy.buytickets.domain.Girl;
import com.sorahjy.buytickets.repository.GirlRepository;
import com.sorahjy.buytickets.service.GirlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    private final static Logger LOGGER=LoggerFactory.getLogger(HTTPAspect.class);

    private final GirlRepository girlRepository;

    @Autowired
    public GirlController(final GirlRepository girlRepository) {this.girlRepository = girlRepository;}

    @Autowired
    GirlService girlService;

    @GetMapping("/girls")
    public List<Girl> girlList() {
        LOGGER.info("girlList");
        return girlRepository.findAll();
    }

    @PostMapping("/girls")
    public Girl girlAdd(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        girl.setId(girl.getId());
        girl.setAge(girl.getAge());
        girl.setUsername(girl.getUsername());
        return girlRepository.save(girl);
    }

    @GetMapping("/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlRepository.findById(id).get();
    }

    @PutMapping("/girls/{id}")
    public void girlUpdata(@PathVariable("id") Integer id,
                           @RequestParam("username") String username,
                           @RequestParam("age") String age) {
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setUsername(username);
        girlRepository.save(girl);
    }

    @DeleteMapping("girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    @GetMapping("/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") String age) {
        return girlRepository.findByAge(age);
    }

    @PostMapping("/girls/two")
    public void girlTwo() {
        girlService.insertTwo();
    }
}
