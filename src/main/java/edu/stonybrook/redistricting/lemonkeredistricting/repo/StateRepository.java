package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.PrecinctGeometry;
import edu.stonybrook.redistricting.lemonkeredistricting.models.State;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query(nativeQuery = true, value = "SELECT p.precinct_id, p.geometry FROM precinct p WHERE p.state_id = (:stateId)")
    List<PrecinctGeometry> findStateGeometry(@Param("stateId") long stateId);
}
