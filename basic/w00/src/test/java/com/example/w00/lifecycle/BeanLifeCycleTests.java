 package com.example.w00.lifecycle;


import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifeCycleTests {

    @Test
    public void lifeCycleTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient networkClient = ac.getBean(NetworkClient.class);
        ac.close();
    }

    static class LifeCycleConfig{
        @Bean
        public NetworkClient networkClient(){
          NetworkClient networkClient = new NetworkClient();
          networkClient.setUrl("http://test.com");
          return networkClient;
        }
    }

}
