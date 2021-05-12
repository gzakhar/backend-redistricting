package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Precinct;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.GeometryMemoryRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.GeometryRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.PrecinctRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.service.GeometryCalculation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(name = "lemonke")
public class PrecinctController {

    @Autowired
    PrecinctRepository precinctRepository;

    @Autowired
    GeometryRepository geometryRepository;

    @GetMapping("precincts/{precinctId}")
    public Precinct getPrecinct(@PathVariable Long precinctId) {
        return precinctRepository.findById(precinctId).orElse(null);
    }

    @GetMapping("precincts/{precinctId}/geometry")
    public JSONObject getPrecinctGeometry(@PathVariable Long precinctId) {

        return Objects.requireNonNull(geometryRepository.findById(precinctId).orElse(null)).getGeometry();
    }

    @GetMapping("precincts/{precinctId}/geometry-memory")
    public JSONObject getPrecinctGeometryMemory(@PathVariable Long precinctId) {

        return GeometryCalculation
                .geometry2Json(Objects
                        .requireNonNull(precinctRepository
                                .findById(precinctId)
                                .orElse(null))
                        .getGeometry());
    }

}
