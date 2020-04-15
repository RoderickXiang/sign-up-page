package roderick.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import roderick.domain.Project;
import roderick.domain.Student;
import roderick.service.ProjectService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/signUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectService projectService = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        Project project = new Project();
        Student student = new Student();
        try {
            BeanUtils.populate(project, parameterMap);
            BeanUtils.populate(student, parameterMap);
            project.setStudent(student);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //添加项目
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("bean.xml");
        projectService = (ProjectService) classPathXmlApplicationContext.getBean("projectService");

        projectService.addProject(project);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
