package edu.stonybrook.redistricting.lemonkeredistricting.repo;

import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictStatRepository extends JpaRepository<DistrictStat, Long> {


    @Query(nativeQuery = true, value="select do.enacted_district_id, t.*\n" +
            "from (\n" +
            "         select d.districting_id,\n" +
            "                d.district_id,\n" +
            "                sum(p.tot_white)    as tot_white,\n" +
            "                sum(p.tot_black)    as tot_black,\n" +
            "                sum(p.tot_hisp)     as tot_hisp,\n" +
            "                sum(p.tot_asian)    as tot_asian,\n" +
            "                sum(p.tot_a_indian) as tot_a_indian,\n" +
            "                sum(p.tot_other)    as tot_other\n" +
            "         from precinct p\n" +
            "                  join district_precinct_map dpm on p.precinct_id = dpm.precinct_id\n" +
            "                  join district d on dpm.district_id = d.district_id\n" +
            "                  join districting d2 on d.districting_id = d2.districting_id\n" +
            "                  join job_districting_map jdm on d2.districting_id = jdm.districting_id\n" +
            "                  join job j on jdm.job_id = j.job_id\n" +
            "         where j.job_id = (:job_id)\n" +
            "         group by d.districting_id, d.district_id\n" +
            "     ) t\n" +
            "         join district_order do using (district_id);")
    List<DistrictStat> findAllByJobId(@Param("job_id") Long jobId);
}
