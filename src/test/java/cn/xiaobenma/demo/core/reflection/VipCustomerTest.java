package cn.xiaobenma.demo.core.reflection;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * @author Ben.Ma <xiaobenma020@gmail.com>
 */
public class VipCustomerTest {

    @Test
    public void forName() throws ClassNotFoundException {
        Class.forName("cn.xiaobenma.demo.core.reflection.VipCustomer");
    }

    @Test
    public void getFields() {
        printFields(getVipCustomerClass().getFields());
    }

    private Class<VipCustomer> getVipCustomerClass() {
        return VipCustomer.class;
    }

    @Test
    public void getField() throws NoSuchFieldException {
        printFields(getVipCustomerClass().getField("VIP_ADVANCED"));
    }


    @Test
    public void getDeclaredFields() {
        printFields(getVipCustomerClass().getDeclaredFields());
    }

    @Test
    public void getDeclaredField() throws NoSuchFieldException {
        printFields(getVipCustomerClass().getDeclaredField("rank"));
    }

    @Test
    public void getMethods() {
        printMethods(getVipCustomerClass().getMethods());
    }

    @Test
    public void getMethod() throws NoSuchMethodException {
        printMethods(getVipCustomerClass().getMethod("setName", String.class));
    }

    @Test
    public void getDeclaredMethods() {
        printMethods(getVipCustomerClass().getDeclaredMethods());
    }

    @Test
    public void getDeclaredMethod() throws NoSuchMethodException {
        printMethods(getVipCustomerClass().getDeclaredMethod("setName", String.class));
    }

    @Test
    public void getConstructors() {
        printConstructors(getVipCustomerClass().getConstructors());
    }

    @Test
    public void getConstructor() throws NoSuchMethodException {
        printConstructors(getVipCustomerClass().getConstructor(String.class, String.class, String.class, int.class));
    }

    @Test
    public void getDeclaredConstructors() {
        printConstructors(getVipCustomerClass().getDeclaredConstructors());
    }

    @Test
    public void getDeclaredConstructor() throws NoSuchMethodException {
        printConstructors(getVipCustomerClass().getDeclaredConstructor());
    }

    @Test
    public void newInstance() throws NoSuchMethodException, IllegalAccessException
            , InvocationTargetException, InstantiationException {
        Constructor<VipCustomer> constructor = getVipCustomerClass().getConstructor(String.class, String.class, String.class, int.class);
        VipCustomer customer = constructor.newInstance("001", "小ben马", "10086", VipCustomer.VIP_ADVANCED);
        Assert.assertEquals("001", customer.getCustomerNo());
        Assert.assertEquals("小ben马", customer.getName());
        Assert.assertEquals("10086", customer.getMobilePhone());
        Assert.assertEquals(VipCustomer.VIP_ADVANCED, customer.getRank());
    }

    @Test
    public void invoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {


        VipCustomer customer = new VipCustomer("001", "小ben马", "10086", VipCustomer.VIP_ADVANCED);
        Assert.assertEquals(VipCustomer.VIP_ADVANCED, customer.getRank());

        Method setRank = getVipCustomerClass().getDeclaredMethod("setRank", int.class);
        setRank.invoke(customer, VipCustomer.VIP_NORMAL);
        Assert.assertEquals(VipCustomer.VIP_NORMAL, customer.getRank());

        Field field = getVipCustomerClass().getDeclaredField("rank");
        field.setAccessible(true);
        Assert.assertEquals(VipCustomer.VIP_NORMAL, field.get(customer));

        field.set(customer, VipCustomer.VIP_ADVANCED);
        Assert.assertEquals(VipCustomer.VIP_ADVANCED, field.get(customer));

    }

    @Test
    public void setAccessible() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException
            , InvocationTargetException, InstantiationException {

        //在VipCustomer中定义类静态常量PRI_NO=100
        Field field = getVipCustomerClass().getDeclaredField("PRI_NO");
        field.setAccessible(true);
        Assert.assertEquals(100, field.get(null));

        //私有构造方法
        Constructor<VipCustomer> constructor = getVipCustomerClass().getDeclaredConstructor();
        constructor.setAccessible(true);
        VipCustomer customer = constructor.newInstance();
        Assert.assertNull(customer.getCustomerNo());
        Assert.assertNull(customer.getName());
        Assert.assertNull(customer.getMobilePhone());

        //调用私有类方法
        Method method = getVipCustomerClass().getDeclaredMethod("doNothingByVipCustomer");
        method.setAccessible(true);
        method.invoke(null);
    }

    //打印变量
    private void printFields(Field... fields) {
        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name + ";");
        }
    }

    //打印方法
    private void printMethods(Method... methods) {
        for (Method method : methods) {
            StringBuilder sb = new StringBuilder("");
            Class retType = method.getReturnType();
            String name = method.getName();

            sb.append("   ");

            // print modifiers, return type and method name
            String modifiers = Modifier.toString(method.getModifiers());
            if (!modifiers.isEmpty()) {
                sb.append(modifiers).append(" ");
            }
            sb.append(retType.getName())
                    .append(" ")
                    .append(name).append("(");

            // print parameter types
            Class[] paramTypes = method.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) {
                    sb.append(", ");
                }
                sb.append(paramTypes[j].getName());
            }
            sb.append(");");
            System.out.println(sb.toString());
        }
    }

    //打印构造方法
    private void printConstructors(Constructor...
                                           constructors) {
        for (Constructor constructor : constructors) {
            StringBuilder sb = new StringBuilder();
            String name = constructor.getName();
            sb.append("   ");

            String modifiers = Modifier.toString(constructor.getModifiers());
            if (!modifiers.isEmpty()) {
                sb.append(modifiers).append(" ");
            }
            sb.append(name).append("(");

            // print parameter types
            Class[] paramTypes = constructor.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) {
                    sb.append(", ");
                }
                sb.append(paramTypes[j].getName());
            }
            sb.append(");");
            System.out.println(sb.toString());
        }
    }


}