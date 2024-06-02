package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {
  @GetMapping("/hello")
  public String hello(Model model){
    model.addAttribute("data","spring");
    return "hello";
  }

  @GetMapping("/hello-string")
  @ResponseBody
  public String helloString(@RequestParam("name") String name, Model model){
   return "Hello " + name;
  }

  @GetMapping("/hello-mvc")
  @ResponseBody
  public Hello helloMvc(@RequestParam("name") String name, Model model){
    Hello hello = new Hello();
    return hello;
  }

  class Hello {
    private String name ;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
