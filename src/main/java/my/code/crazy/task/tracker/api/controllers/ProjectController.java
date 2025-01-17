package my.code.crazy.task.tracker.api.controllers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.code.crazy.task.tracker.api.dto.ProjectDto;
import my.code.crazy.task.tracker.api.exception.BadRequestExeption;
import my.code.crazy.task.tracker.api.factories.ProjectDtoFactory;
import my.code.crazy.task.tracker.store.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults
@Transactional
@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectDtoFactory projectDtoFactory;

    private final String CREATE_PROJECT = "/api/projects";

    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name) {
        projectRepository.findByName(name)
                         .ifPresent(project -> {
                             throw new BadRequestExeption(String.format("Project \"%s\" already exists", name));
                         });

        return null;

        // return projectDtoFactory.makeProjectDto();
    }
}