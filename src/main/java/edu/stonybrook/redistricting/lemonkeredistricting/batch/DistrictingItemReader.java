package edu.stonybrook.redistricting.lemonkeredistricting.batch;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import edu.stonybrook.redistricting.lemonkeredistricting.repo.DistrictingRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import javax.annotation.Resource;


public class DistrictingItemReader implements ItemReader<Districting> {

    @Resource
    private DistrictingRepository districtingRepository;
    private static long ids[] = {48};

    private int pointer = 0;


    @Override
    public Districting read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (pointer < ids.length) return districtingRepository.findById(ids[pointer++]).orElse(null);
        return null;
    }
}
