package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.Precinct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecinctRepository extends JpaRepository<Precinct, Long> {

    List<Precinct> getPrecinctByStateId(Long id);

    @Query(nativeQuery = true, value = "select p.precinct_id,\n" +
            "       p.state_id,\n" +
            "       p.tot_white,\n" +
            "       p.tot_black,\n" +
            "       p.tot_hisp,\n" +
            "       p.tot_asian,\n" +
            "       p.tot_a_indian,\n" +
            "       p.tot_other,\n" +
            "       p.vap_white,\n" +
            "       p.vap_black,\n" +
            "       p.vap_hisp,\n" +
            "       p.vap_asian,\n" +
            "       p.vap_a_indian,\n" +
            "       p.vap_other,\n" +
            "       p.cvap_white,\n" +
            "       p.cvap_black,\n" +
            "       p.cvap_hisp,\n" +
            "       p.cvap_asian,\n" +
            "       p.cvap_a_indian,\n" +
            "       p.cvap_other\n" +
            "from precinct p\n" +
            "         inner join district_precinct_map dpm on p.precinct_id = dpm.precinct_id\n" +
            "where dpm.district_id = (:id)")
    List<Precinct> getPrecinctByDistrictId(Long id);
}
