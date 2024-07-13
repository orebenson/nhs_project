package nhs.uhdb.NHS_project.accounts.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private JdbcTemplate jdbc;
    private RowMapper<User> userMapper;
    private BCryptPasswordEncoder passwordEncoder;

    public UserRepositoryImpl(JdbcTemplate aJdbc, BCryptPasswordEncoder passwordEncoder) {
        this.jdbc = aJdbc;
        this.passwordEncoder = passwordEncoder;
        setUserMapper();
    }

    private void setUserMapper() {
        this.userMapper = (resultSet, i) -> {
            User user = new User();
            user.setUser_id(resultSet.getLong("user_id"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setPassword(resultSet.getString("password"));
            user.setAddress1(resultSet.getString("address1"));
            user.setAddress2(resultSet.getString("address2"));
            user.setCity(resultSet.getString("city"));
            user.setPostcode(resultSet.getString("postcode"));
            return user;
        };
    }

    @Override
    public Long getUserIdByEmail(String email) {
        String sql = "SELECT user_id FROM user_table WHERE email = ?";
        return jdbc.queryForObject(sql, Long.class, email);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user_table WHERE email = ?";
        User user = jdbc.queryForObject(sql, userMapper, email);
        if (user != null) user.setPassword("");
        return user;
    }

    @Override
    public User getUserByUserId(Long user_id) {
        String sql = "SELECT * FROM user_table WHERE user_id = ?";
        User user = jdbc.queryForObject(sql, userMapper, user_id);
        if (user != null) user.setPassword("");
        return user;
    }

    @Override
    public Long createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String sql = "INSERT INTO user_table (email, firstname, lastname, password, enabled, address1, address2, city, postcode) VALUES (?,?,?,?,?,?,?,?,?) RETURNING user_id";
        Long user_id = jdbc.queryForObject(sql, Long.class, user.getEmail(), user.getFirstname(), user.getLastname(), encodedPassword, true, user.getAddress1(), user.getAddress2(), user.getCity(), user.getPostcode());
        String roles_sql = "insert into users_roles (user_id, role_id) values (?, 2) RETURNING user_id";
        jdbc.queryForObject(roles_sql, Long.class, user_id);
        return user_id;
    }

    @Override
    public Long createAdminUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String sql = "INSERT INTO user_table (email, firstname, lastname, password, enabled, address1, address2, city, postcode) VALUES (?,?,?,?,?,?,?,?,?) RETURNING user_id";
        Long user_id = jdbc.queryForObject(sql, Long.class, user.getEmail(), user.getFirstname(), user.getLastname(), encodedPassword, true, user.getAddress1(), user.getAddress2(), user.getCity(), user.getPostcode());
        String roles_sql = "insert into users_roles (user_id, role_id) values (?, 1) RETURNING user_id";
        jdbc.queryForObject(roles_sql, Long.class, user_id);
        return user_id;
    }

    @Override
    public Boolean deleteUserByEmail(String email) {
        String sql = "DELETE FROM user_table WHERE email = ?";
        int rowsAffected = jdbc.update(sql, email);
        return rowsAffected > 0;
    }

    @Override
    public Boolean deleteUserByUserId(Long user_id) {
        String sql = "DELETE FROM user_table WHERE user_id = ?";
        int rowsAffected = jdbc.update(sql, user_id);
        return rowsAffected > 0;
    }
}
