package pl.coderslab.spirnghibernate.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
        import java.time.LocalTime;

@Controller
public class HelloController {

    @GetMapping("")
    public String hello() {
        return "hello";
    }


}