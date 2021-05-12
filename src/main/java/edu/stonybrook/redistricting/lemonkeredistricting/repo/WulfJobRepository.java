package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WulfJobRepository extends JpaRepository<Job, Long> {

    @Query(nativeQuery = true, value="SELECT * FROM Job j where j.state_id = (:id)")
    List<Job> findAllByStateId(Long id);
}
