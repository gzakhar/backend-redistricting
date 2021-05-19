package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictSummaryRepository extends JpaRepository<DistrictSummary, Long> {

}
