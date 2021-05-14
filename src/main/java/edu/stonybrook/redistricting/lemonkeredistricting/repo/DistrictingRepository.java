package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictingRepository extends JpaRepository<Districting, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Districting d INNER JOIN Job_districting_map jdm ON d.districting_id = jdm.districting_id WHERE jdm.job_id = (:id)")
    List<Districting> findAllByJobId(@Param("id") long id);

    @Query(nativeQuery = true, value = "select d2.districting_id\n" +
            "from districting d\n" +
            "inner join job_districting_map jdm on d.districting_id = jdm.districting_id\n" +
            "inner join job j on jdm.job_id = j.job_id\n" +
            "inner join state s on j.state_id = s.state_id\n" +
            "inner join districting d2 on s.enacted_districting_id = d2.districting_id\n" +
            "where d.districting_id = (:id)")
    Optional<Districting> findEnactedByDistrictingId(@Param("id") long id);
}
