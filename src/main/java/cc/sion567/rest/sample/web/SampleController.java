package cc.sion567.rest.sample.web;

import cc.sion567.rest.sample.vo.Rate;
import com.google.common.collect.Lists;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String doo2(@RequestParam(name="name",required=false) String name){
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


    @RequestMapping(value = "/testObject",method = RequestMethod.GET)
    public List<Rate> testObject(){
        List<Rate> list = Lists.newArrayList();
        Rate r1 = new Rate();
        r1.setName("aa");
        r1.setCode("AAA");
        r1.setRate(5.3);
        Rate r2 = new Rate();
        r2.setName("bb");
        r2.setCode("BBB");
        r2.setRate(15.3);
        Rate r3 = new Rate();
        r3.setName("cc");
        r3.setCode("CCC");
        r3.setRate(7.3);

        list.add(r1);
        list.add(r2);
        list.add(r3);

        return list;
    }


}
