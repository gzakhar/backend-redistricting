package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.*;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.PrecinctRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/lemonke")
public class DistrictController {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PrecinctRepository precinctRepository;

    @GetMapping("/districts")
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @GetMapping("/districts/{districtId}")
    public District getDistrictById(@PathVariable Long districtId) {
        return districtRepository.findById(districtId).orElse(null);
    }

    @GetMapping("/districts/{districtId}/precincts")
    public List<Precinct> getPrecinctsByDistrictId(@PathVariable Long districtId) {

        return precinctRepository.getPrecinctByDistrictId(districtId);
    }

    @GetMapping("/districts/{districtId}/is-majority-minority")
    public Map<Ethnicity, Boolean> getMMDistricting(@PathVariable Long districtId) {

        District district = districtRepository.findById(districtId).orElse(null);
        if (district != null) {
            return district.isMajorityMinority();
        }
        return null;
    }

    @GetMapping("/districts/{districtId}/geometry")
    public JSONObject getGeometry(@PathVariable Long districtId) {

        Geometry geometry = Objects.requireNonNull(districtRepository.findById(districtId).orElse(null)).getGeometry();
        return GeometryCalculation.geometry2Json(geometry);
    }

    @GetMapping("/districts/{districtId}/perimeter")
    public Double getPerimeter(@PathVariable Long districtId) {
        return Objects.requireNonNull(districtRepository.findById(districtId).orElse(null)).getPerimeter();
    }

    @GetMapping("/districts/{districtId}/area")
    public Double getArea(@PathVariable Long districtId) {
        return Objects.requireNonNull(districtRepository.findById(districtId).orElse(null)).getArea();
    }

    @GetMapping("/districts/{districtId}/population/{populationType}")
    public Integer getPopulationByPopulationType(@PathVariable Long districtId, @PathVariable PopulationType populationType) {



        return Objects.requireNonNull(districtRepository.findById(districtId).orElse(null))
                .getTotalPopulation(populationType);
    }

}
