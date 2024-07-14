package com.example.w00.beanfind;

import com.example.w00.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTests {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("All Bean")
    void findAllBean(){
        String[] beanDefinitions = ac.getBeanDefinitionNames();
        for (String beanDefinition : beanDefinitions){
            Object bean = ac.getBean(beanDefinition);
            System.out.println("name =" + beanDefinition + " object = " + bean );
        }
    }

    @Test
    @DisplayName("Application Bean")
    void findApplicationBean(){
        String[] beanDefinitions = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitions){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);

                System.out.println("name =" + beanDefinitionName + " object = " + bean );
            }
        }
    }
}
