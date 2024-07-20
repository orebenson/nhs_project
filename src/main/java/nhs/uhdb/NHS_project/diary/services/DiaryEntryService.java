package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.DiaryEntry;

import java.time.LocalDate;

public interface DiaryEntryService {
    Long createDiaryEntry(DiaryEntry diaryEntry);
    DiaryEntry getDiaryEntryByUserIdAndDate(Long user_id, LocalDate date);
}
