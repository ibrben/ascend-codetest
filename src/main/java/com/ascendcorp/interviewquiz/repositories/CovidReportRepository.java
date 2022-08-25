package com.ascendcorp.interviewquiz.repositories;

import com.ascendcorp.interviewquiz.entities.CovidReportEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidReportRepository extends JpaRepository<CovidReportEntity, Long> {

    Optional<List<CovidReportEntity>> findByDateIs(String date);

}
