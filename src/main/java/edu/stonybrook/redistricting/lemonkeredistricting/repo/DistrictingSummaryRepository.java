package edu.stonybrook.redistricting.lemonkeredistricting.repo;

//import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictSummary;
import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictingSummaryRepository extends JpaRepository<DistrictingSummary, Long> {

    @Query(nativeQuery = true, value = "select d.districting_id\n" +
            "from districting d\n" +
            "         left outer join districting_summary ds on d.districting_id = ds.districting_summary_id\n" +
            "where ds.districting_summary_id is null\n" +
            "order by districting_id;")
    List<Long> findAbsentDistrictingSummaryId();

    @Query(nativeQuery = true, value = "SELECT ds.*\n" +
            "FROM job j\n" +
            "INNER JOIN job_districting_map jdm ON j.job_id = jdm.job_id\n" +
            "INNER JOIN districting d ON jdm.districting_id = d.districting_id\n" +
            "INNER JOIN districting_summary ds ON d.districting_id = ds.districting_summary_id\n" +
            "WHERE j.job_id = (:id);")
    List<DistrictingSummary> findDistrictingSummaryByJobId(@Param("id") Long id);

}
