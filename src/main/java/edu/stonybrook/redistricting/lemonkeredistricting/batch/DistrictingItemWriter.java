package edu.stonybrook.redistricting.lemonkeredistricting.batch;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.batch.item.ItemWriter;

import javax.annotation.Resource;
import java.util.List;

public class DistrictingItemWriter implements ItemWriter<DistrictingSummary> {

    @Resource
    private DistrictingSummaryRepository districtingSummaryRepository;

    @Override
    public void write(List<? extends DistrictingSummary> list) throws Exception {
        for (var item : list) {
            districtingSummaryRepository.save(item);
        }
    }
}
