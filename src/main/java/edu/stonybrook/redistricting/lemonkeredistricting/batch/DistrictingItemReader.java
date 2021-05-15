package edu.stonybrook.redistricting.lemonkeredistricting.batch;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;


public class DistrictingItemReader implements ItemReader<Districting> {

    @Autowired
    private DistrictingSummaryRepository districtingSummaryRepository;

    @Autowired
    private DistrictingRepository districtingRepository;

    private Iterator<Long> iterator;

    @PostConstruct
    public void init() {
        List<Long> absentid = districtingSummaryRepository.findAbsentDistrictingSummaryId();
        iterator = absentid.iterator();
    }

    @Override
    public Districting read() {

        if (iterator.hasNext())
            return districtingRepository.findById(iterator.next()).orElse(null);
        else
            return null;
    }
}
