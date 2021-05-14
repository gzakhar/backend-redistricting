package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistrictingSummaryRepository extends JpaRepository<DistrictingSummary, Long> {

    @Query(nativeQuery = true, value = "select d.districting_id\n" +
            "from districting d\n" +
            "         left outer join districting_summary ds on d.districting_id = ds.districting_summary_id\n" +
            "where ds.districting_summary_id is null\n" +
            "order by districting_id\n" +
            "limit 1;;")
    Optional<Long> findAbsentDistrictingSummaryId();
}
