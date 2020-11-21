package hello.hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello2")
    public String hello(Model model){
        model.addAttribute("data","hel12lo");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name); // template 파일 내에도 name변수를 써줘야 에러가 안난다(내 20분)
        // name을 model에 넣는다.

        return "hello-spring"; // templates 내의 html 파일과 동일하게 해줘야
    }

    @GetMapping("hello-string")
    @ResponseBody // http의 body부에 해당 데이터를 직접 넣어주겠다, 따로 html에 넣는 것이 아니라
    public String helloString(@RequestParam("name") String name, Model model){
        return "hello " + name;
    }


    @GetMapping("hello-api") //json 방식, key value로 이뤄진 구조
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 위에서는 문자를 리턴했지만, 지금은 hello라는 객체를 리턴 => json방식으로 데이터를 만들어 http에 던지겠다: JSON 컨버터가 동작
    }
    static class Hello{

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
