package secondspring.secondspring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class HelloController {
    @GetMapping("hoospring")
    public String Namewhatever(Model model) {
        model.addAttribute("value", "keep going");
        return "hello";
    }
    @GetMapping("hello-mvc")
    public String HelloMvc(@RequestParam("value") ArrayList anyvalue, Model model){
        model.addAttribute("value", anyvalue);
        return "hello-mvc";
    }
    @GetMapping("hello-string")
    @ResponseBody // http의 body부에 해당 데이터를 직접 넣어주겠다, 따로 html에 넣는 것이 아니라
    public String helloString(@RequestParam("name") String value, Model model){
        // url에 name을 넣어줘야한다. 요구되는 파라미터가 name이다.
        // ResponseBody: template 없이 데이터를 주입한다.
        // hello-string이라는 url만 선점
        return value; // return 하는 것도 template이 아닌, 변수명
    }
    static class Hello{
        private String hello2;
        public String getHello2() {
            return hello2;
        }
        public void setHello2(String hello3) {
            this.hello2 = hello3;
        }
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String value){
        Hello hello2 = new Hello();
        hello2.setHello2(value);
        return hello2;
    }

//    @GetMapping("hello-api") //json 방식, key value로 이뤄진 구조
//    @ResponseBody
//    public Hello helloApi(@RequestParam("name") String name){
//        Hello hello = new Hello();
//        hello.setName(name);
//        return hello; // 위에서는 문자를 리턴했지만, 지금은 hello라는 객체를 리턴 => json방식으로 데이터를 만들어 http에 던지겠다: JSON 컨버터가 동작
//    }


}