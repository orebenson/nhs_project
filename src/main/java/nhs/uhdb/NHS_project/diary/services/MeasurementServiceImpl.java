package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.Measurement;
import nhs.uhdb.NHS_project.diary.model.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService{

    private MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public List<Measurement> getEmptyMeasurementsByUserId(Long user_id) {
        return measurementRepository.getEmptyMeasurementsByUserId(user_id);
    }

    @Override
    public List<Long> submitMeasurementsForDiaryEntry(List<Measurement> measurements, Long diary_entry_id) {
        return measurementRepository.submitMeasurementsForDiaryEntry(measurements, diary_entry_id);
    }
}
