package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.model.DiaryEntryRepository;
import nhs.uhdb.NHS_project.diary.model.ProgressData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartServiceImpl implements ChartService {

    private final DiaryEntryRepository diaryEntryRepository;

    @Autowired
    public ChartServiceImpl(DiaryEntryRepository diaryEntryRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
    }

    @Override
    public List<ProgressData> getMetricData(Long userId, String metric) {
        List<DiaryEntry> entries = diaryEntryRepository.getDiaryEntriesByUserId(userId);
        List<ProgressData> metrics = new ArrayList<>();
        for (DiaryEntry entry : entries) {
            ProgressData data = new ProgressData();
            data.setDate(entry.getCreatedAt());
            int value = 0;
            switch (metric) {
                case "weight":
                    value = entry.getWeight();
                    break;
                case "wellnessScore":
                    value = entry.getWellnessScore();
                    break;
                case "qualityOfLifeScore":
                    value = entry.getQualityOfLifeScore();
                    break;
            }
            data.setValue(value);
            metrics.add(data);
        }
        return metrics;
    }
}
