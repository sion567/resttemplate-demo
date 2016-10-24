package cc.sion567.rest.sample.web;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @RequestMapping("/foo1")
    public String foo1(){
        return "hello world";
    }

    @RequestMapping("/foo2")
    public String foo2(@RequestParam String name){
        return "!hello "+name;
    }


    @RequestMapping("/foo3/{name}")
    public String foo3(@PathVariable String name){
        return "!hello "+name;
    }


    @RequestMapping(value = "/do1",method = RequestMethod.POST)
    public String doo1(String name){
        return "~hello "+name;
    }

    @RequestMapping(value = "/do2",method = RequestMethod.POST)
    public String doo2(@RequestParam("name") String name){
        return "~hello "+name;
    }


    @RequestMapping(value = "/do3",method = RequestMethod.POST)
    public String doo3(@RequestBody String name){
        return "~hello "+name;
    }

    @RequestMapping(value = "/do4",method = RequestMethod.POST)
    public String doo4(RequestEntity<String> requestEntity){
        return "~hello "+requestEntity.getBody();
    }

}
