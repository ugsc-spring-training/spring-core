package com.example.democore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

@Configuration
//@Import(AnotherConfig.class)
@ImportResource("/xmlconfig/config.xml")
@ComponentScan
@PropertySource("/props/data.properties")
public class DemoCoreApplication {

    @Value("#{constClass.const}")
    String name;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DemoCoreApplication.class, args);

//		MyRepository myRepository = new MyRepository();
//		MyService myService = new MyService(myRepository);
//
//		System.out.println(myService.findUser());

		MyService myService = ctx.getBean(MyService.class);
		System.out.println(myService.findUser());

//		StringClass stringClass = ctx.getBean("example", StringClass.class);
//		System.out.println(stringClass.hello());

		Environment env = ctx.getEnvironment();
        System.out.println(env.getProperty("miso.myusername"));

        AnnotatedClass annotatedClass = ctx.getBean(AnnotatedClass.class);
        System.out.println(annotatedClass.login("Jano"));

        Hello hello = ctx.getBean("stringTwoClass" ,Hello.class);
        System.out.println(hello.hello());
	}

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public MyRepository myRepository() {
		return new MyRepository(name);
	}

	@Bean
	public MyService myService() {
		return new MyService(myRepository());
	}

	@Bean public ConstClass constClass() {
	    return new ConstClass();
    }
}

class MyRepository {
    private final String name;

    MyRepository(String name) {
        this.name = name;
    }

    public String findUser() {
		return name;
	}

//	@PostConstruct
	void init() {
        System.out.println("myrepository postconstruct");
    }

//    @PreDestroy
    void destroy() {
        System.out.println("myrepository predestroy");
    }
}

class MyService {
	private final MyRepository myRepository;


	MyService(MyRepository myRepository) {
		this.myRepository = myRepository;
	}

	public String findUser() {
		return myRepository.findUser();
	}
}

class ConstClass {
    public String getConst() {
        return "const";
    }
}

@Service
class AnnotatedClass {

    private  MyRepository myRepository;

    public boolean login(String username) {
        return this.myRepository.findUser().equals(username);
    }

    @Autowired
    public void setMyRepository(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public MyRepository getMyRepository() {
        return myRepository;
    }
}


