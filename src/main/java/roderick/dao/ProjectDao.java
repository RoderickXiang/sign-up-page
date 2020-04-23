package roderick.dao;

import org.springframework.stereotype.Repository;
import roderick.domain.Project;

import java.util.List;

@Repository
public interface ProjectDao {
    void addProject(Project newProject);

    List<Project> getAllProjects();
}
