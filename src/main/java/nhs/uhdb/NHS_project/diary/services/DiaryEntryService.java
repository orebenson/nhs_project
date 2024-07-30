package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.model.ProgressData;

import java.time.LocalDate;
import java.util.List;

public interface DiaryEntryService {
    Long createDiaryEntry(DiaryEntry diaryEntry);
    DiaryEntry getDiaryEntryByUserIdAndDate(Long user_id, LocalDate date);
    List<DiaryEntry> getDiaryEntriesByUserId(Long user_id);
    List<String> getFormattedDiaryEntryDatesByUserId(Long user_id);
    List<ProgressData> getMetricData(Long userId, String metric);
}
