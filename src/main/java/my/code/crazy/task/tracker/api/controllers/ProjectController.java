package my.code.crazy.task.tracker.api.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.code.crazy.task.tracker.api.factories.ProjectDtoFactory;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults
@Transactional
@RestController
public class ProjectController {
    private final ProjectDtoFactory projectDtoFactory;
}

