package nhs.uhdb.NHS_project.diary.model;

import java.time.LocalDate;
import java.util.List;

public interface DiaryEntryRepository {
    Long createDiaryEntry(DiaryEntry diaryEntry);
    DiaryEntry getDiaryEntryByUserIdAndDate(Long user_id, LocalDate date);
    List<DiaryEntry> getDiaryEntriesByUserId(Long user_id);
    
}
