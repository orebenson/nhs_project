package nhs.uhdb.NHS_project.diary.model;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {

    private JdbcTemplate jdbc;
    private RowMapper<Photo> photoRowMapper;

    public PhotoRepositoryImpl(JdbcTemplate aJdbc) {
        this.jdbc = aJdbc;
        setPhotoRowMapper();
    }

    private void setPhotoRowMapper() {
        this.photoRowMapper = (resultSet, i) -> {
            Photo photo = new Photo();
            photo.setId(resultSet.getLong("photo_id"));
            photo.setBytes(resultSet.getBytes("photo"));
            return photo;
        };
    }

    @Override
    public Long createPhoto(Photo photo) {
        String sql = "INSERT INTO photos (photo) VALUES (?) RETURNING photo_id";
        try {
            return jdbc.queryForObject(sql, Long.class, new Object[]{photo.getBytes()});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean deletePhotoById(Long photo_id) {
        String sql = "DELETE FROM photos WHERE photo_id = ?";
        String sql2 = "DELETE FROM diary_entry_photos WHERE photo_id = ?";
        try {
            int rowsAffected = jdbc.update(sql, photo_id);
            int rowsAffected2 = jdbc.update(sql2, photo_id);
            return rowsAffected2 + rowsAffected > 0;
        } catch (Error e) {
            return false;
        }
    }

    @Override
    public Photo getPhotoById(Long photo_id) {
        String sql = "SELECT * FROM photos WHERE photo_id = ?";
        try {
            return jdbc.queryForObject(sql, photoRowMapper, photo_id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Photo> getPhotosByDiaryEntryId(Long diary_entry_id) {
        String sql = "SELECT * FROM  photos p JOIN diary_entry_photos dep on p.photo_id = dep.photo_id WHERE dep.diary_entry_id = ?";
        try {
            return jdbc.query(sql, photoRowMapper, diary_entry_id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Boolean submitPhotosForDiaryEntryId(List<Photo> photos, Long diary_entry_id) {
        String sql = "INSERT INTO diary_entry_photos (diary_entry_id, photo_id) VALUES (?,?)";
        try {
            int rowsAffected = 0;
            for(Photo photo : photos) {
                Long photo_id = createPhoto(photo);
                rowsAffected += jdbc.update(sql, diary_entry_id, photo_id);
            }
            return rowsAffected > 0;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
