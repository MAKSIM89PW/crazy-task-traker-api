package my.code.crazy.task.tracker.api.factories;

import my.code.crazy.task.tracker.api.dto.ProjectDto;
import my.code.crazy.task.tracker.api.dto.TaskDto;
import my.code.crazy.task.tracker.store.entities.ProjectEntity;
import my.code.crazy.task.tracker.store.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoFactory {

    public TaskDto makeProjectDto(TaskEntity entity) {
        return TaskDto.builder()
                      .id(entity.getId())
                      .name(entity.getName())
                      .createdAt(entity.getCreateAt())
                      .description(entity.getDescription())
                      .build();
    }
}
