package com.example.w00.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

public class SingletonTests {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 =ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 =ac.getBean(SingletonBean.class);
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }


    @Scope("singleton")
    //@Configuration
    static class SingletonBean{

        @PostConstruct
        public void init(){
            System.out.println("Singleton.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("Singleton.close");
        }
    }


}
