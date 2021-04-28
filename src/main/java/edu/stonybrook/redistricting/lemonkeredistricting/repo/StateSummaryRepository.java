package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.StateSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateSummaryRepository extends JpaRepository<StateSummary, Long> {

    @Query(nativeQuery = true, value="SELECT state_id, name FROM state")
    List<StateSummary> findAll();
}
