package edu.stonybrook.redistricting.lemonkeredistricting.repo;

//import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictSummary;
import edu.stonybrook.redistricting.lemonkeredistricting.models.DistrictingSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictingSummaryRepository extends JpaRepository<DistrictingSummary, Long> {

    @Query(nativeQuery = true, value = "select d.districting_id\n" +
            "from districting d\n" +
            "         left outer join districting_summary ds on d.districting_id = ds.districting_summary_id\n" +
            "where ds.districting_summary_id is null\n" +
            "order by districting_id;")
    List<Long> findAbsentDistrictingSummaryId();

    @Query(nativeQuery = true, value = "SELECT ds.*\n" +
            "FROM job j\n" +
            "INNER JOIN job_districting_map jdm ON j.job_id = jdm.job_id\n" +
            "INNER JOIN districting d ON jdm.districting_id = d.districting_id\n" +
            "INNER JOIN districting_summary ds ON d.districting_id = ds.districting_summary_id\n" +
            "WHERE j.job_id = (:id);")
    List<DistrictingSummary> findDistrictingSummaryByJobId(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select ds.districting_summary_id,\n" +
            "       ds.district_count,\n" +
            "       ds.geometric_compactness,\n" +
            "       ds.graph_compactness,\n" +
            "       ds.population_compactness,\n" +
            "       sum(case when (d2.tot_white / d2.tot_pop) > (:threshold) then 1 else 0 end)    as mm_white,\n" +
            "       sum(case when (d2.tot_black / d2.tot_pop) > (:threshold) then 1 else 0 end)    as mm_black,\n" +
            "       sum(case when (d2.tot_hisp / d2.tot_pop) > (:threshold) then 1 else 0 end)     as mm_hispanic,\n" +
            "       sum(case when (d2.tot_asian / d2.tot_pop) > (:threshold) then 1 else 0 end)    as mm_asian,\n" +
            "       sum(case when (d2.tot_a_indian / d2.tot_pop) > (:threshold) then 1 else 0 end) as mm_amind,\n" +
            "       sum(case when (d2.tot_other / d2.tot_pop) > (:threshold) then 1 else 0 end)    as mm_other,\n" +
            "       ds.total_population,\n" +
            "       ds.va_population,\n" +
            "       ds.cva_population,\n" +
            "       ds.total_population_equality,\n" +
            "       ds.va_population_equality,\n" +
            "       ds.cva_population_equality\n" +
            "from job j\n" +
            "         inner join job_districting_map jdm on j.job_id = jdm.job_id\n" +
            "         inner join districting d on jdm.districting_id = d.districting_id\n" +
            "         inner join districting_summary ds on d.districting_id = ds.districting_summary_id\n" +
            "         inner join district d2 on d.districting_id = d2.districting_id\n" +
            "where j.job_id = (:id)\n" +
            "group by ds.districting_summary_id;")
    List<DistrictingSummary> findDistrictingSummaryByJobId(@Param("id") Long id, @Param("threshold") Double threshold);

}
