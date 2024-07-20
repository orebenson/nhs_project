package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.model.DiaryEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
}
