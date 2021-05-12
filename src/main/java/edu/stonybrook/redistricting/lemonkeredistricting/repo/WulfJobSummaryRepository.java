package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.JobSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WulfJobSummaryRepository extends JpaRepository<JobSummary, Long> {

    @Query(nativeQuery = true, value = "SELECT j.*, COUNT(*) AS number_districtings\n" +
            "FROM job j\n" +
            "         INNER JOIN job_districting_map jdm ON j.job_id = jdm.job_id\n" +
            "         INNER JOIN districting d ON jdm.districting_id = d.districting_id\n" +
            "WHERE j.state_id = (:id)\n" +
            "GROUP BY j.job_id;")
    List<JobSummary> findByStateId(@Param("id") long id);

}
