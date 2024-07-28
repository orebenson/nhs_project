package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.LymphoedemaType;
import nhs.uhdb.NHS_project.admin.model.LymphoedemaTypeRepository;
import nhs.uhdb.NHS_project.admin.model.LymphoedemaTypeRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LymphoedemaTypeServiceImpl implements LymphoedemaTypeService {

    LymphoedemaTypeRepository lymphoedemaTypeRepository;

    public LymphoedemaTypeServiceImpl(LymphoedemaTypeRepository lymphoedemaTypeRepository) {
        this.lymphoedemaTypeRepository = lymphoedemaTypeRepository;
    }

    @Override
    public Long createLymphoedemaType(LymphoedemaType lymphoedema_type) {
        return lymphoedemaTypeRepository.createLymphoedemaType(lymphoedema_type);
    }

    @Override
    public Boolean deleteLymphoedemaTypeById(Long id) {
        return lymphoedemaTypeRepository.deleteLymphoedemaTypeById(id);
    }

    @Override
    public List<LymphoedemaType> getAllLymphoedemaTypes() {
        return lymphoedemaTypeRepository.getAllLymphoedemaTypes();
    }

    @Override
    public Boolean setUserLymphoedemaType(Long user_id, Long lymphoedema_type_id) {
        return lymphoedemaTypeRepository.setUserLymphoedemaType(user_id, lymphoedema_type_id);
    }

    @Override
    public LymphoedemaType getLymphoedemaTypeByUserId(Long user_id) {
        return lymphoedemaTypeRepository.getLymphoedemaTypeByUserId(user_id);
    }

    @Override
    public LymphoedemaType getLymphoedemaTypeByUserEmail(String email) {
        return lymphoedemaTypeRepository.getLymphoedemaTypeByUserEmail(email);
    }
}
