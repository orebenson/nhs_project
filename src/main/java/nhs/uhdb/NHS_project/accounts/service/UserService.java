package nhs.uhdb.NHS_project.accounts.service;

import nhs.uhdb.NHS_project.accounts.model.User;

public interface UserService {
    Long getUserIdByEmail(String email);
    User getUserByEmail(String email);
    User getUserByUserId(Long user_id);

    Long createUser(User user);

    Boolean deleteUserByEmail(String email);
    Boolean deleteUserByUserId(Long user_id);
}
