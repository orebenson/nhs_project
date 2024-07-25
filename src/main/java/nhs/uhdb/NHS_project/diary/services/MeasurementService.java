package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.Measurement;

import java.util.List;

public interface MeasurementService {
    List<Measurement> getEmptyMeasurementsByUserId(Long user_id);
    List<Long> submitMeasurementsForDiaryEntry(List<Measurement> measurements, Long diary_entry_id);
}
