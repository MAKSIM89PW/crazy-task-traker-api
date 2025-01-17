package my.code.crazy.task.tracker.api.controllers;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import my.code.crazy.task.tracker.api.dto.ProjectDto;
import my.code.crazy.task.tracker.api.exception.BadRequestExeption;
import my.code.crazy.task.tracker.api.exception.NotFoundException;
import my.code.crazy.task.tracker.api.factories.ProjectDtoFactory;
import my.code.crazy.task.tracker.store.entities.ProjectEntity;
import my.code.crazy.task.tracker.store.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectDtoFactory projectDtoFactory;

    private final String CREATE_PROJECT = "/api/projects";
    private final String EDIT_PROJECT = "/api/projects/{project_id}";

    @PostMapping(CREATE_PROJECT)
    public ProjectDto createProject(@RequestParam String name) {

        if(name.trim().isEmpty()) {
            throw new BadRequestExeption("Project name is empty");
        }

        projectRepository.findByName(name)
                         .ifPresent(project -> {
                             throw new BadRequestExeption(String.format(
                                     "Project \"%s\" already exists",
                                     name));
                         });

        ProjectEntity project = projectRepository.saveAndFlush(
                ProjectEntity.builder()
                             .name(name)
                             .build()
        );

        return projectDtoFactory.makeProjectDto(project);
    }

    @PatchMapping(EDIT_PROJECT)
    public ProjectDto editPatch(@PathVariable("project_id") Long projectId, @RequestParam String name) {

        if(name.trim().isEmpty()) {
            throw new BadRequestExeption("Project name is empty");
        }

        ProjectEntity project = projectRepository
                .findById(projectId)
                .orElseThrow(
                        () -> new NotFoundException(String.format("Project with \"%s\" doesn exist", projectId)));

        projectRepository
                .findByName(name)
                .filter(anotherProject -> !Objects.equals(anotherProject.getId(), projectId))
                .ifPresent(anotherProject -> {
                    throw new BadRequestExeption(String.format("Project \"%s\" already exists", name));
                });
        project.setName(name);
        project = projectRepository.saveAndFlush(project);

        return projectDtoFactory.makeProjectDto(project);
    }
}