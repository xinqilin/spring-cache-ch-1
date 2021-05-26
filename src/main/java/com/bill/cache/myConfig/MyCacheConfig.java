package com.bill.cache.myConfig;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {

            @Override
            public Object generate(Object target, Method method, Object... params) {
                // TODO Auto-generated method stub
                System.out.println("自訂key" + method.getName() + "[" + Arrays.asList(params).toString() + "]");
                return method.getName() + "[" + Arrays.asList(params).toString() + "]";
            }
        };
    }
}
