package cn.xiaobenma.demo.core.reflection;

import lombok.Getter;
import lombok.Setter;

/**
 * 定义普通客户
 * @author Ben.Ma <xiaobenma020@gmail.com>
 */
@Setter
@Getter
public class Customer {

    private String customerNo; //客户编号
    private String name;       //客户名称
    private String mobilePhone; //手机

    public static final String NAME_CUSTOMER_NO = "客户编号";

    private static void doNothingByCustomer() {
        //do nothing
    }

    public static void doSomethingByCustomer() {
        //do something
    }

    public Customer(String customerNo, String name, String mobilePhone) {
        this.customerNo = customerNo;
        this.name = name;
        this.mobilePhone = mobilePhone;
    }
}
