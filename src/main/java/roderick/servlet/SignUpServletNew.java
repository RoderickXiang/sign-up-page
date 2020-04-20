package roderick.servlet;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpServletNew {
    @RequestMapping(path = "/signUpServlet")
    public String signUp() {
        System.out.println("执行");
        return "success";
    }
}
