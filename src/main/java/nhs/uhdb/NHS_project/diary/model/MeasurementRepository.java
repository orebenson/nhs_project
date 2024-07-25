package nhs.uhdb.NHS_project.diary.model;

import java.util.List;

public interface MeasurementRepository {
    List<Measurement> getEmptyMeasurementsByUserId(Long user_id);
    List<Long> submitMeasurementsForDiaryEntry(List<Measurement> measurements, Long diary_entry_id);
    List<MeasurementType> getMeasurementTypesByLymphoedemaTypeId(Long lymphoedema_type_id);
    List<Measurement> getMeasurementsByDiaryEntryId(Long diary_entry_id);
    Long createMeasurementType(MeasurementType measurementType);


}
