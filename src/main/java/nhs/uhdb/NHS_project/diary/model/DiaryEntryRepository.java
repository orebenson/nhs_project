package nhs.uhdb.NHS_project.diary.model;

import java.time.LocalDate;

public interface DiaryEntryRepository {
    Long createDiaryEntry(DiaryEntry diaryEntry);
    DiaryEntry getDiaryEntryByUserIdAndDate(Long user_id, LocalDate date);
}
