package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.District;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Ethnicity;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Precinct;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.PrecinctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/lemonke")
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PrecinctRepository precinctRepository;

    @GetMapping("/districts")
    public List<District> getAllDistricts(){
        return districtRepository.findAll();
    }

    @GetMapping("/districts/{districtId}")
    public Optional<District> getDistrictById(@PathVariable Long districtId){
        return districtRepository.findById(districtId);
    }

    @GetMapping("/districts/{districtId}/precincts")
    public List<Precinct> getPrecinctsByDistrictId(@PathVariable Long districtId){

        return precinctRepository.getPrecinctByDistrictId(districtId);
    }

    @GetMapping("/districts/{districtId}/is-majority-minority")
    public Map<Ethnicity, Boolean> getMMDistricting(@PathVariable Long districtId){

        District district = districtRepository.findById(districtId).orElse(null);
        if (district != null){
            return district.isMajorityMinority();
        }
        return null;
    }

}
