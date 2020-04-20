package roderick.service.Impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import roderick.dao.ProjectDao;
import roderick.domain.Project;
import roderick.service.ProjectService;

import java.io.IOException;
import java.io.InputStream;

public class ProjectServiceImpl implements ProjectService {
    ProjectDao projectDao;
    SqlSession sqlSession;
    SqlSessionFactory sqlSessionFactory;

    //mybatis代码块
    {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        projectDao = sqlSession.getMapper(ProjectDao.class);
        try {
            if (inputStream != null)
                inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProject(Project newProject) {
        projectDao.addProject(newProject);
        sqlSession.commit();
    }
}
