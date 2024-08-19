package nhs.uhdb.NHS_project.questionnaire.service;

import nhs.uhdb.NHS_project.questionnaire.model.QolResponseArm;
import nhs.uhdb.NHS_project.questionnaire.model.QolResponseArmRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class QolResponseArmServiceImpl implements QolResponseArmService {
    QolResponseArmRepository qolResponseArmRepository;

    public QolResponseArmServiceImpl(QolResponseArmRepository qolResponseArmRepository) {
        this.qolResponseArmRepository = qolResponseArmRepository;
    }

    @Override
    public QolResponseArm getResponseByUserIdAndDate(Long user_id, LocalDate date) {
        return qolResponseArmRepository.getResponseByUserIdAndDate(user_id, date);
    }

    @Override
    public Long saveResponse(QolResponseArm qolResponseArm) {
        return qolResponseArmRepository.saveResponse(qolResponseArm);
    }

    @Override
    public List<QolResponseArm> getResponsesByUserId(Long user_id) {
        return qolResponseArmRepository.getResponsesByUserId(user_id);
    }

    @Override
    public QolResponseArm getResponseById(Long id) {
        return qolResponseArmRepository.getResponseById(id);
    }
}
