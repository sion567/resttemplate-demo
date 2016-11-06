package cc.sion567.rest.sample.web;

import cc.sion567.rest.sample.vo.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.Random;
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

    @RequestMapping("/foo3")
    public WebAsyncTask<String> foo3(){
        System.out.println("foo3......");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello world<1>";
            }
        };
        return new WebAsyncTask<String>(1000, callable);//将异步处理的超时时间设为1s
    }

    //https://github.com/spring-projects/spring-mvc-showcase

    @RequestMapping(value = "/doo1",method = RequestMethod.POST)
    public Callable<String> doo1(@ModelAttribute("user") final User u)   {
        String a = null;
        System.out.println("doo1......");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello "+u.getName()+"~";
            }
        };
    }

    @RequestMapping(value = "/doo2",method = RequestMethod.POST)
    public String doo2(@RequestBody final User u)   {
        System.out.println("doo2......");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello "+u.getName()+"~";
    }

    @RequestMapping("/ex")
    public Callable<String> callableWithException() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                int num=new Random().nextInt(11);//0~10
                if (num == 0)
                    throw new IllegalStateException("!!!error!!!");
                else
                    throw new IllegalArgumentException("!error!");
            }
        };
    }
    @ExceptionHandler
    @ResponseBody
    public String handleException(IllegalStateException ex) {
        return "Handled ex: " + ex.getMessage();
    }


    @RequestMapping(value = "/too1/{name}")
    public Callable<String> too1(@PathVariable String name)   {
        System.out.println("too1......");
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello "+name;
            }
        };
    }
    @RequestMapping(value = "/too2/{name}")
    public String too2(@PathVariable String name)   {
        System.out.println("too2......");
        return "hello "+name;
    }
}
