package com.rowan.proxy;

import java.lang.reflect.Proxy;

public class Test {
	public static void main(String[] args) {
		//1、创建目标对象
		RealClass realClass = new RealClass();
		
		//2、创建代理对象处理者实例
		DynamicProxy dynamicProxy = new DynamicProxy(realClass);
		
		Class<?>[] interfaces =new Class[]{Interface.class,Interface2.class};
		//创建代理对象实例（将方法调用指派给代理对象
		Interface proxy = (Interface)Proxy.newProxyInstance(Interface.class.getClassLoader(), interfaces, dynamicProxy);
		proxy.speak();
	}
}
