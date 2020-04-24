package roderick.service;

import roderick.domain.Project;

import java.util.List;

public interface ProjectService {
    void addProject(Project newProject);

    List<Project> getAllProject();
}
