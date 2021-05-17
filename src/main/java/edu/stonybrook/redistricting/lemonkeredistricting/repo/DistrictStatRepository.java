package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictStatRepository extends JpaRepository<DistrictStat, Long> {


    @Query(nativeQuery = true, value="select d.districting_id,\n" +
            "       d.district_id,\n" +
            "       sum(p.tot_white) as tot_white,\n" +
            "       sum(p.tot_black) as tot_black,\n" +
            "       sum(p.tot_hisp)  as tot_hisp,\n" +
            "       sum(p.tot_asian) as tot_asian,\n" +
            "       sum(p.tot_a_indian) as tot_a_indian,\n" +
            "       sum(p.tot_other) as tot_other,\n" +
            "       sum(p.tot_white + p.tot_black + p.tot_hisp + p.tot_asian + p.tot_a_indian + p.tot_other) as total_pop\n" +
            "from precinct p\n" +
            "         join district_precinct_map dpm on p.precinct_id = dpm.precinct_id\n" +
            "         join district d on dpm.district_id = d.district_id\n" +
            "where d.districting_id in (:ids)\n" +
            "group by d.districting_id, d.district_id;")
    List<DistrictStat> findAllByDistrictingIds(@Param("ids") List<Long> districtingIds);

    @Query(nativeQuery = true, value="select d2.district_id,\n" +
            "       d2.districting_id,\n" +
            "       sum(p.tot_white) as tot_white,\n" +
            "       sum(p.tot_black) as tot_black,\n" +
            "       sum(p.tot_hisp) as tot_hisp,\n" +
            "       sum(p.tot_asian) as tot_asian,\n" +
            "       sum(p.tot_a_indian) as tot_a_indian,\n" +
            "       sum(p.tot_other) as tot_other,\n" +
            "       sum(p.tot_white + p.tot_black + p.tot_hisp + p.tot_asian + p.tot_a_indian + p.tot_other) as total_pop\n" +
            "from districting d\n" +
            "inner join district d2 on d.districting_id = d2.districting_id\n" +
            "inner join district_precinct_map dpm on d2.district_id = dpm.district_id\n" +
            "inner join precinct p on dpm.precinct_id = p.precinct_id\n" +
            "where d.districting_id = (:id)\n" +
            "group by d2.district_id")
    List<DistrictStat> findDistrictStatById(@Param("id") Long districtingId);
}
