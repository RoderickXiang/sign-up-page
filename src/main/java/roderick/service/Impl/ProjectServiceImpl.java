package roderick.service.Impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roderick.dao.ProjectDao;
import roderick.domain.Project;
import roderick.service.ProjectService;

import java.io.IOException;
import java.io.InputStream;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao;

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /*不进行整合的话就使用mybatis生产代理的对象*/

    @Override
    public void addProject(Project newProject) {
        projectDao.addProject(newProject);
    }
}
