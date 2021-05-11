package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictingSummaryRepository extends JpaRepository<DistrictingSummary, Long> {
}
