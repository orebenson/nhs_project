package nhs.uhdb.NHS_project.admin.model;

import java.util.List;

public interface LymphoedemaTypeRepository {
    Long createLymphoedemaType(LymphoedemaType lymphoedema_type);
    Boolean deleteLymphoedemaTypeById(Long id);
    List<LymphoedemaType> getAllLymphoedemaTypes();
    Boolean setUserLymphoedemaType(Long user_id, Long lymphoedema_type_id);
    LymphoedemaType getLymphoedemaTypeByUserId(Long user_id);
    LymphoedemaType getLymphoedemaTypeByUserEmail(String email);
}
