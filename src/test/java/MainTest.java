import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import roderick.dao.ProjectDao;
import roderick.domain.Project;
import roderick.domain.Student;
import roderick.service.Impl.ProjectServiceImpl;
import roderick.service.ProjectService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainTest {
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    ProjectDao projectDao;

    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        projectDao = sqlSession.getMapper(ProjectDao.class);
    }

    @After
    public void end() throws IOException {
        inputStream.close();
        sqlSession.close();
    }


    @Test
    public void daoTest() throws IOException {


        List<Project> allProjects = projectDao.getAllProjects();
        for (Project project : allProjects) {
            System.out.println(project);
        }

        inputStream.close();
        sqlSession.close();
    }

    @Test
    public void insertTest() throws IOException {

        Student student = new Student();
        student.setStudentId("656559");
        student.setStudentName("测试");
        Project project = new Project();
        project.setStudent(student);
        project.setProjectName("test");

        ProjectService projectService = new ProjectServiceImpl();
        projectService.addProject(project);

        inputStream.close();
        sqlSession.close();
    }

    @Test
    public void serviceTest() throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ProjectService projectService = (ProjectService) applicationContext.getBean("projectService");
        List<Project> allProject = projectService.getAllProject();
        for (Project project : allProject) {
            System.out.println(project);
        }
    }
}
