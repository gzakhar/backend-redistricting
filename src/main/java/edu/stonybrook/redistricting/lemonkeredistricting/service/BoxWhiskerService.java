package edu.stonybrook.redistricting.lemonkeredistricting.service;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictStat;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BoxWhiskerService {


    @Autowired
    private DistrictStatRepository districtStatRepository;

    public Map<Long, List<DistrictStat>> calculateBoxWhisker(Long jobId) {

        return districtStatRepository.findAllByJobId(jobId)
                .stream()
                .collect(Collectors.groupingBy(DistrictStat::getEnacted_district_id));

    }

}
