package com.gusi.platform.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring beans操作类，根据bean的名称获取相应bean对象
 * 
 * @author dyy_gusi 2014年12月31日上午9:09:54
 * 
 */
public class SpringFactory implements ApplicationContextAware
{

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException
    {
        context = applicationContext;
    }

    public ApplicationContext getApplicationContext()
    {
        return context;
    }

    /**
     * 通过bean的名称获取Spring容器里面的bean对象
     * 
     * @param beanName
     *            bean的名称
     * @return Object类型的bean对象
     */
    public static Object getBean(String beanName)
    {
        return context.getBean(beanName);
    }

    /**
     * 通过bean的名称获取Spring容器里卖年的bean对象
     * 
     * @param beanName
     *            bean的名称
     * @param clazz
     *            返回的对象class类型
     * @return 返回指定类型的bean对象
     */
    public static <T> T getBean(String beanName, Class<T> clazz)
    {
        return context.getBean(beanName, clazz);
    }

}
