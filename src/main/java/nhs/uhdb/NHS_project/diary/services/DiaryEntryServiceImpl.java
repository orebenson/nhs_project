package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.model.DiaryEntryRepository;
import nhs.uhdb.NHS_project.diary.model.ProgressData;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryEntryServiceImpl implements DiaryEntryService {
    private DiaryEntryRepository diaryEntryRepository;

    public DiaryEntryServiceImpl(DiaryEntryRepository diaryEntryRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
    }

    @Override
    public Long createDiaryEntry(DiaryEntry diaryEntry) {
        return diaryEntryRepository.createDiaryEntry(diaryEntry);
    }

    @Override
    public DiaryEntry getDiaryEntryByUserIdAndDate(Long user_id, LocalDate date) {
        return diaryEntryRepository.getDiaryEntryByUserIdAndDate(user_id, date);
    }

    @Override
    public List<DiaryEntry> getDiaryEntriesByUserId(Long user_id) {
        return diaryEntryRepository.getDiaryEntriesByUserId(user_id);
    }

    @Override
    public List<String> getFormattedDiaryEntryDatesByUserId(final Long userId) {
        List<DiaryEntry> entries = diaryEntryRepository.getDiaryEntriesByUserId(userId);
        List<String> formattedDates = new ArrayList<>();
        if (entries != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for (DiaryEntry entry : entries) {
                LocalDate createdAt = entry.getCreatedAt();
                String formattedDate = createdAt.format(formatter);
                formattedDates.add(formattedDate);
            }
        }
        return formattedDates;
    }

    @Override
    public List<ProgressData> getMetricData(Long userId, String metric) {
        List<DiaryEntry> entries = diaryEntryRepository.getDiaryEntriesByUserId(userId);
        List<ProgressData> metrics = new ArrayList<>();
        for (DiaryEntry entry : entries) {
            ProgressData data = new ProgressData();
            data.setDate(entry.getCreatedAt());
            switch (metric) {
                case "weight":
                    data.setValue(entry.getWeight());
                    break;
                case "wellnessScore":
                    data.setValue(entry.getWellnessScore());
                    break;
                case "qualityOfLifeScore":
                    data.setValue(entry.getQualityOfLifeScore());
                    break;
            }
            metrics.add(data);
        }
        return metrics;
    }

}
