package cn.xiaobenma.demo.core.reflection.proxy;

/**
 * 定义鹦鹉
 * @author Ben.Ma <xiaobenma020@gmail.com>
 */
public class Parrot implements Bird {

    private String name;

    public Parrot(String name) {
        this.name = name;
    }

    @Override
    public void sing() {
        System.out.println("Parrot[" + getName() + "] sing a song!");
    }

    @Override
    public String getName() {
        return name;
    }
}
