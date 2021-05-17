package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictStat;
import edu.stonybrook.redistricting.lemonkeredistricting.models.Ethnicity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BoxWhiskerService {

    public Map<Long, List<DistrictStat>>  getBoxplotData(List<DistrictStat> items, final Ethnicity ethnicity){

        Map<Long, List<DistrictStat>> map = items.stream().collect(Collectors.groupingBy(DistrictStat::getDistricting_id));
        map.values().forEach(list ->
                list.sort((o1, o2) -> {
                    Double d1 = o1.getPopulationPercentage(ethnicity);
                    Double d2 = o2.getPopulationPercentage(ethnicity);
                    return d1.compareTo(d2);
                }));
        return map;
    }

    public List<Double> getBoxplotNData(Map<Long, List<DistrictStat>> map, final Ethnicity ethnicity, int index){
        return map.values().stream().map(list->list.get(index).getPopulationPercentage(ethnicity)).collect(Collectors.toList());
    }
}
