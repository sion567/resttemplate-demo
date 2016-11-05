package cc.sion567.rest.sample.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/aysnc")
public class SampleController2 {

    @RequestMapping("/foo1")
     public Callable<String> foo1(){
        System.out.println("foo1......");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello world<1>";
            }
        };
    }

    @RequestMapping("/foo2")
    public String foo2(){
        System.out.println("foo2......");
        return "hello world<2>";
    }

    @RequestMapping(value = "/doo",method = RequestMethod.POST)
    public Callable<String> doo(@RequestBody final String name)   {
        System.out.println("doo......");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello "+name+"~";
            }
        };
    }
}
