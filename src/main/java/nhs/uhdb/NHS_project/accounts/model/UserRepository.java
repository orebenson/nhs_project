package nhs.uhdb.NHS_project.accounts.model;

public interface UserRepository {
    Long getUserIdByEmail(String email);
    User getUserByEmail(String email);
    User getUserByUserId(Long user_id);

    Long createUser(User user);
    Long createAdminUser(User user);

    Boolean deleteUserByEmail(String email);
    Boolean deleteUserByUserId(Long user_id);
}
