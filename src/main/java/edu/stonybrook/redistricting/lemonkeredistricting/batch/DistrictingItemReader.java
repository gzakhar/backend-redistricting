package edu.stonybrook.redistricting.lemonkeredistricting.batch;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingSummaryRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;


public class DistrictingItemReader implements ItemReader<Districting> {

    @Autowired
    private DistrictingSummaryRepository districtingSummaryRepository;

    @Autowired
    private DistrictingRepository districtingRepository;

//    private final static ArrayList<Long> ids = (ArrayList<Long>) districtingSummaryRepository.findAbsentDistrictingSummaryIds();
//    private int pointer = 0;

    @Override
    public Districting read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        Long absentid = districtingSummaryRepository.findAbsentDistrictingSummaryId().orElse(null);
        if (absentid != null) return districtingRepository.findById(absentid).orElse(null);

        return null;
    }
}
