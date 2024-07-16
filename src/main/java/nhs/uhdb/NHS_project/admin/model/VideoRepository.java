package nhs.uhdb.NHS_project.admin.model;

public interface VideoRepository {
    Long createVideo(String videoLink);
    Video getVideoById(Long video_id);
}
