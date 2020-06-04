package com.rowan.proxy;

public class RealClass implements Interface,Interface2 {

	@Override
	public void speak() {
		System.out.println("speak-大家好我是真正的类，我是渣渣会");
	}

	@Override
	public void say() {
		System.out.println("say-大家好我是真正的类，我是渣渣会");
	}
}
