package com.example.w00.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTests {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        System.out.println("find prototypeBean1 = " + prototypeBean1);
        System.out.println("find prototypeBean1 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        prototypeBean1.close();
        prototypeBean2.close();

        ac.close();
    }


    @Scope("prototype")
    //@Configuration
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("Prototype.init");
        }

        @PreDestroy
        public void close(){
            System.out.println("Prototype.close");
        }
    }
}
