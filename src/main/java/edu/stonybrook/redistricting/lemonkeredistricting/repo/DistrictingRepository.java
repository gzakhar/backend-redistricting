package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Districting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DistrictingRepository extends CrudRepository<Districting, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Districting d INNER JOIN Job_districting_map jdm ON d.id = jdm.districting_id WHERE jdm.job_id = (:id)")
    List<Districting> findAllByJobId(@Param("id") long id);
}
