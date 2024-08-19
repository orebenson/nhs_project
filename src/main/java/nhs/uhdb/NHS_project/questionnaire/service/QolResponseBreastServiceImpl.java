package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.QolResponseBreast;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponseBreastRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class QolResponseBreastServiceImpl implements QolResponseBreastService {
    QolResponseBreastRepository qolResponseBreastRepository;

    public QolResponseBreastServiceImpl(QolResponseBreastRepository qolResponseBreastRepository) {
        this.qolResponseBreastRepository = qolResponseBreastRepository;
    }

    @Override
    public QolResponseBreast getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return qolResponseBreastRepository.getResponseByUserIdAndDate(user_id, date);
    }

    @Override
    public Long saveResponse(QolResponseBreast qolResponseBreast) {
        return qolResponseBreastRepository.saveResponse(qolResponseBreast);
    }

    @Override
    public List<QolResponseBreast> getResponsesByUserId(Long user_id) {
        return qolResponseBreastRepository.getResponsesByUserId(user_id);
    }

    @Override
    public QolResponseBreast getResponseById(Long id) {
        return qolResponseBreastRepository.getResponseById(id);
    }
}
