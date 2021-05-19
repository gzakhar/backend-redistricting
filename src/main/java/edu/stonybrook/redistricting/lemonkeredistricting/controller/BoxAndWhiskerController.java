package edu.stonybrook.redistricting.lemonkeredistricting.controller;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictStat;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Ethnicity;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictStatRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.service.BoxWhiskerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lemonke")
public class BoxAndWhiskerController {

    @Autowired
    private DistrictStatRepository districtStatRepository;

    @Autowired
    private BoxWhiskerService boxWhiskerService;

    @GetMapping("/box-whisker/{districtingId}/{ethnicity}/dots")
    public List<Double> getRedDots(@PathVariable Long districtingId, @PathVariable Ethnicity ethnicity){

        return districtStatRepository.findDistrictStatById(districtingId)
                .stream()
                .map(districtStat -> districtStat.getPopulationPercentage(ethnicity))
                .sorted(Comparator.comparingDouble(Double::doubleValue))
                .collect(Collectors.toList());
    }

    @GetMapping("/box-whisker/{districtingIds}/{ethnicity}/background")
    public List<List<Double>> getBackground(@PathVariable Long[] districtingIds, @PathVariable Ethnicity ethnicity){


        List<DistrictStat> stats = districtStatRepository.findAllByDistrictingIds(Arrays.asList(districtingIds));
        Map<Long, List<DistrictStat>> planStats = boxWhiskerService.getBoxplotData(stats, ethnicity);
        Long key = (Long) planStats.keySet().toArray()[0];

        List<List<Double>> res = new ArrayList<>(planStats.size());
        for(int i = 0; i < planStats.get(key).size(); i ++){

            res.add(boxWhiskerService.getBoxplotNData(planStats, ethnicity, i));
        }

        return res;
    }


}
