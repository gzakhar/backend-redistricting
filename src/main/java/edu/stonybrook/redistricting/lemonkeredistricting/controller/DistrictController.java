package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.District;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("lemonke")
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;


    @GetMapping("/districts")
    public List<District> getAllDistricts(){
        return districtRepository.findAll();
    }

    @GetMapping("/districts/{id}")
    public Optional<District> getDistrictById(@PathVariable Long id){
        return districtRepository.findById(id);
    }

}
