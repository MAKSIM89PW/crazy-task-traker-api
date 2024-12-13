package my.code.crazy.task.tracker.api.factories;

import my.code.crazy.task.tracker.api.dto.ProjectDto;
import my.code.crazy.task.tracker.api.dto.TaskStateDto;
import my.code.crazy.task.tracker.store.entities.ProjectEntity;
import my.code.crazy.task.tracker.store.entities.TaskStateEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskStateDtoFactory {

    public TaskStateDto makeTaskStateDto(TaskStateEntity entity) {
        return TaskStateDto.builder()
                           .id(entity.getId())
                           .name(entity.getName())
                           .createdAt(entity.getCreateAt())
                           .ordinal(entity.getOrdinal())
                           .build();
    }
}
