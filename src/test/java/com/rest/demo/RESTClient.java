package com.rest.demo;

import com.google.common.collect.Maps;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * Created by hgg on 2016/10/18.
 */
public class RESTClient {

    final static String _url_ = "http://localhost:8080/";


    public static void main(String[] args) {
        RestTemplate restTemplate =new RestTemplate();
        System.out.println("[foo1]>>" + (restTemplate.getForObject(_url_ + "foo1", String.class)));
        System.out.println("[foo2]>>" + (restTemplate.getForObject(_url_ + "foo2?name=huahua", String.class)));
        System.out.println("[foo3]>>" + (restTemplate.getForObject(_url_ + "foo3/caocao", String.class)));
        Map<String, String> vars = Collections.singletonMap("name", "caocao");
        System.out.println("[foo4]>>" + (restTemplate.getForObject(_url_ + "foo3/{name}", String.class,vars)));

//http://blog.csdn.net/luccs624061082/article/details/40893963
        System.out.println("===========");
        Map<String,String> map = Maps.newHashMap();
        map.put("name", "abc");
        System.out.println("[do1]>>" + (restTemplate.postForObject(_url_ + "do1", null, String.class, map)));
        System.out.println("[do1]>>" + (restTemplate.postForObject(_url_ + "do1?name=abc", null, String.class)));
        System.out.println("[do2]>>" + (restTemplate.postForObject(_url_ + "do2", null, String.class, map)));

        HttpHeaders headers = new HttpHeaders();
        // 设置Accept表示浏览器支持的MIME类型,此处意思是要返回的类型
        headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE));
        //置ContentType标明数据是JSON数据,否则报415(Unsupported Media Type),必须和REST接口的RequestMapping的ContentType对应
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        System.out.println("[do1]>>" + (restTemplate.exchange(_url_ + "do1", HttpMethod.POST, entity, String.class, "AAAA")).getBody());

        HttpEntity<Object> requestEntity1 = new HttpEntity<Object>("ABCDEFG", headers);
        HttpEntity<Object> requestEntity2 = new HttpEntity<Object>("{\"name\":\"ABCDEFG\"}", headers);

        System.out.println("[do3]>>" + (restTemplate.exchange(_url_ + "do3", HttpMethod.POST, requestEntity1, String.class)).getBody());
        System.out.println("[do4]>>" + (restTemplate.exchange(_url_ + "do4", HttpMethod.POST, requestEntity2, String.class)).getBody());


//        RequestEntity<String> requestEntity = new RequestEntity<String>(headers, HttpMethod.POST, URI.create("/proto/read"));

    }



    public void demo(){
        // Resource resource = new ClassPathResource("appcontext.xml");
// BeanFactory factory = new XmlBeanFactory(resource);

//用classpath路径也可以
// ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:appcontext.xml");
// ApplicationContext factory=new ClassPathXmlApplicationContext("appcontext.xml");

// ClassPathXmlApplicationContext 使用了file前缀也是可以使用绝对路径的
// ApplicationContext factory=new ClassPathXmlApplicationContext("file:G:/1Java实用项目资源/2Spring/1精通Spring全 Jar代码/workspace/workspace/example6/src/appcontext.xml");

//用文件系统的路径 默认是指项目的根路径
// ApplicationContext factory=new FileSystemXmlApplicationContext("src/appcontext.xml");

//使用了 classpath: 前缀,作为标志, 这样,FileSystemXmlApplicationContext 也能够读入classpath下的相对路径
// ApplicationContext factory=new FileSystemXmlApplicationContext("classpath:appcontext.xml");
// ApplicationContext factory=new FileSystemXmlApplicationContext("file:G:/1Java实用项目资源/2Spring/1精通Spring全 Jar代码/workspace/workspace/example6/src/appcontext.xml");

//也可以不加file前缀
//        ApplicationContext factory=new FileSystemXmlApplicationContext("G:/1Java实用项目资源/2Spring/1精通Spring全Jar代码 /workspace/workspace/example6/src/appcontext.xml");
//
//        IHelloWorld hw = (IHelloWorld) factory.getBean("helloworldbean");
//        log.info(hw.getContent("luoshifei"));
    }

}
