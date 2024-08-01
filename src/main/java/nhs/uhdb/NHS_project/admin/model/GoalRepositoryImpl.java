package nhs.uhdb.NHS_project.admin.model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class GoalRepositoryImpl {
    private JdbcTemplate jdbc;
    private RowMapper<Goal> GoalMapper;
    public GoalRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setGoalMapper();
    }

    private void setGoalMapper() {
        this.GoalMapper = (resultset, i) -> {
            Goal goal = new Goal();
            goal.setId(resultset.getLong("id"));
            goal.setWeight(resultset.getInt("weight"));
            goal.setDeadline(resultset.getObject("end_date", LocalDate.class));
            return goal;
        };
    }
}
