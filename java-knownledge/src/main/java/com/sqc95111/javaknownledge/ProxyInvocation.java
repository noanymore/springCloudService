package com.sqc95111.javaknownledge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName ${Name}
 * @Description 动态代理实用类
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/4/8 10:48
 * @Version 1.0
 */
public class ProxyInvocation implements InvocationHandler {
    static public Logger logger  = LoggerFactory.getLogger(InvocationHandler.class);
    private Object target;

    public ProxyInvocation(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("动态代理前置方法");
        return method.invoke(target, args);
    }

    public static void main(String[] args) {
        InterfaceService target = new InterfaceServiceImpl();
        logger.info(target.getClass().getName());
        ProxyInvocation proxyInvocation = new ProxyInvocation(target);
        //logger.info(proxyInvocation.getClass().getClassLoader().getParent().toString());
        InterfaceService proxyObject = (InterfaceService) Proxy.newProxyInstance(ProxyInvocation.class.getClassLoader(),
                                                                      target.getClass().getInterfaces(), proxyInvocation);
        proxyObject.method();


        AspectJProxyFactory factory = new AspectJProxyFactory(target);
//        factory.addAspect(AspectAopClass.class);
        factory.addAspect(Aspect1.class);
        InterfaceService proxy = factory.getProxy();
        proxy.method();
    }
}
