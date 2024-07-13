package nhs.uhdb.NHS_project.accounts.service;

import nhs.uhdb.NHS_project.accounts.model.User;
import nhs.uhdb.NHS_project.accounts.model.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.getUserIdByEmail(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserByUserId(Long user_id) {
        return userRepository.getUserByUserId(user_id);
    }

    @Override
    public Long createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public Long createAdminUser(User user) {
        return userRepository.createAdminUser(user);
    }

    @Override
    public Boolean deleteUserByEmail(String email) {
        return userRepository.deleteUserByEmail(email);
    }

    @Override
    public Boolean deleteUserByUserId(Long user_id) {
        return userRepository.deleteUserByUserId(user_id);
    }

}
