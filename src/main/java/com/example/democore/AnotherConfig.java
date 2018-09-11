package com.example.democore;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class AnotherConfig {
//    @Autowired
//    MyRepository myRepository;

//    @Bean
//    public StringClass example(MyRepository myRepository) {
//        return new StringClass(myRepository.findUser());
//    }
}

@Component
class StringClass implements Hello {
    private final String hello;

    StringClass(@Value("one") String hello) {
        this.hello = hello;
    }

    public String hello() {
        return hello;
    }
}

@Component
class StringTwoClass implements Hello {
    private final String hello;


    StringTwoClass(@Value("two") String hello) {
        this.hello = hello;
    }

    public String hello() {
        return hello;
    }
}

@Component
class TestAutowire {
    private final Hello stringTwoClass;

    private String text;

    @Value("exists value")
    public void setText(String text) {
        this.text = text;
    }

    TestAutowire(Hello stringTwoClass) {
        this.stringTwoClass = stringTwoClass;
    }

    @PostConstruct
    void init() {
        System.out.println(text);
    }

    @PreDestroy
    void ende() {
        System.out.println("i am dying");
    }
}

interface Hello {
    public String hello();
}
