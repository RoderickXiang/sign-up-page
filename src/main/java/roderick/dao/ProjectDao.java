package roderick.dao;

import roderick.domain.Project;

import java.util.List;

public interface ProjectDao {
    void addProject(Project newProject);

    List<Project> getAllProjects();
}
