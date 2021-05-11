package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.PrecinctGeometry;
import edu.stonybrook.redistricting.lemonkeredistricting.models.State;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    @Query(nativeQuery = true, value = "SELECT p.precinct_id, p.geometry FROM state s INNER JOIN precinct p ON s.state_id = p.state_id;")
    public PrecinctGeometry findStateGeometry();
}
