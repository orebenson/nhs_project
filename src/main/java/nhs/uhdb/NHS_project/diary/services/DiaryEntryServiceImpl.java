package nhs.uhdb.NHS_project.diary.services;

import nhs.uhdb.NHS_project.diary.model.DiaryEntry;
import nhs.uhdb.NHS_project.diary.model.DiaryEntryRepository;
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

}
