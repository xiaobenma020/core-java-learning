package cn.xiaobenma.demo.core.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java动态代理代理相关demo
 * @author Ben.Ma <xiaobenma020@gmail.com>
 */
public class BirdProxy {


    private BirdProxy() {

    }

    public static Bird create(Bird bird) {
        return (Bird) Proxy.newProxyInstance(Bird.class.getClassLoader()
                , new Class<?>[] {Bird.class}, new BirdHandler(bird));
    }

    static class BirdHandler implements InvocationHandler {

        private final Bird bird; //被代理的对象

        private BirdHandler(Bird bird) {
            this.bird = bird;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return method.invoke(bird, args);
        }
    }

    public static void main(String[] args) {
        Bird bird =  create(new Parrot("BB"));
        bird.sing();
    }

}
