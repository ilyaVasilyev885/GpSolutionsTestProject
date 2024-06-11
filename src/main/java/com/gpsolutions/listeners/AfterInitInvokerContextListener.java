package com.gpsolutions.listeners;

import com.gpsolutions.annotations.AfterInit;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class AfterInitInvokerContextListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ConfigurableListableBeanFactory factory;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String originalClassName = beanDefinition.getBeanClassName();
            if (originalClassName == null) {
                continue;
            }
            Class<?> originalClass = Class.forName(originalClassName);
            Method[] methods = originalClass.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(AfterInit.class)) {
                    Object bean = context.getBean(name);
                    Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                    currentMethod.invoke(bean);
                }
            }
        }
    }
}
