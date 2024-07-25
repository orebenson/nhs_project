package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.admin.model.LymphoedemaType;

import java.util.List;

public interface LymphoedemaTypeService {
    Long createLymphoedemaType(LymphoedemaType lymphoedema_type);
    Boolean deleteLymphoedemaTypeById(Long id);
    List<LymphoedemaType> getAllLymphoedemaTypes();
    Boolean setUserLymphoedemaType(Long user_id, Long lymphoedema_type_id);
    LymphoedemaType getLymphoedemaTypeByUserId(Long user_id);
    LymphoedemaType getLymphoedemaTypeByUserEmail(String email);
}
