package nhs.uhdb.NHS_project.admin.service;

import nhs.uhdb.NHS_project.diary.model.ProgressData;

import java.util.List;

public interface ChartService {
    List<ProgressData> getMetricData(Long userId, String metric);

}
