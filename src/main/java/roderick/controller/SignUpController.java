package roderick.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import roderick.domain.Project;
import roderick.domain.Student;
import roderick.service.ProjectService;

import java.util.HashMap;

@Controller
public class SignUpController {
    ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/signUpServlet")
    public @ResponseBody
    String signUp(Project project, Student student) throws JsonProcessingException {
        HashMap<String, String> hashMap = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        String json;

        project.setStudent(student);
        //使用服务层添加数据，封装json
        try {
            projectService.addProject(project);
            hashMap.put("msg", "success");
            json = objectMapper.writeValueAsString(hashMap);    //成功
        } catch (Exception e) {
            hashMap.put("msg", "fail");
            json = objectMapper.writeValueAsString(hashMap);   //失败
            e.printStackTrace();
        }
        //返回json数据
        return json;
    }
}
