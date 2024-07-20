package nhs.uhdb.NHS_project.admin.model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class VideoRepositoryImpl implements VideoRepository{

    private JdbcTemplate jdbc;
    private RowMapper<Video> videoRowMapper;

    public VideoRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setVideoRowMapper();
    }

    private void setVideoRowMapper() {
        this.videoRowMapper = (resultSet, i) -> {
            Video video = new Video();
            video.setId(resultSet.getLong("video_id"));
            video.setVideoLink(resultSet.getString("video_link"));
            return video;
        };
    }

    @Override
    public Long createVideo(String videoLink) {
        String sqlVideo = "INSERT INTO videos (video_link) VALUES (?) RETURNING video_id";
        return jdbc.queryForObject(sqlVideo, Long.class, videoLink );
    }

    @Override
    public Video getVideoById(Long video_id) {
        String sql = "SELECT * FROM videos WHERE video_id = ?";
        try {
            return jdbc.queryForObject(sql, videoRowMapper, video_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}
