package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.PrecinctGeometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrecinctGeometryRepository extends JpaRepository<PrecinctGeometry, Long> {

    @Query(nativeQuery = true, value = "select precinct_id, geometry FROM precinct WHERE state_id = (:id)")
    List<PrecinctGeometry> findAllByStateId(long id);
}
