package in.sameerit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sameerit.entity.WeatherRecord;

public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {
}

