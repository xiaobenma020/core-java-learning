package cn.xiaobenma.demo.core.reflection;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;

/**
 * @author Ben.Ma <xiaobenma020@gmail.com>
 */
public class ArrayTest {

    @Test
    public void newInstance() {
        //一维数组
        Object array = Array.newInstance(String.class, 2);
        Array.set(array, 0, "小ben马");
        Array.set(array, 1, "xiaobenma");

        Assert.assertEquals("小ben马", Array.get(array, 0));
        Assert.assertEquals("xiaobenma", Array.get(array, 1));
    }


    @Test
    public void newInstance2() {
        //多维数组
        //String[2][1]
        Object arrays = Array.newInstance(String.class, 2, 1);

        Object array0 = Array.newInstance(String.class, 1);
        Array.set(array0, 0, "小ben马");
        Array.set(arrays, 0, array0);

        Object array1 = Array.newInstance(String.class, 1);
        Array.set(array1, 0, "xiaobenma");
        Array.set(arrays, 1, array1);

        Assert.assertEquals("小ben马", Array.get(Array.get(arrays, 0), 0));
        Assert.assertEquals("xiaobenma", Array.get(Array.get(arrays, 1), 0));
    }
}
