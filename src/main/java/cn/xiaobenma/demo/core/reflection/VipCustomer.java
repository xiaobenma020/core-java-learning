package cn.xiaobenma.demo.core.reflection;

import lombok.Getter;
import lombok.Setter;

/**
 * 定义普通客户
 * @author Ben.Ma <xiaobenma020@gmail.com>
 */
@Setter
@Getter
public class VipCustomer extends Customer {

    private int rank; //客户等级， 1：高级，2：普通

    public static final int VIP_ADVANCED = 1;
    public static final int VIP_NORMAL = 2;

    private static final int PRI_NO = 100;

    public VipCustomer(String customerNo, String name, String mobilePhone, int rank) {
        super(customerNo, name, mobilePhone);
        this.rank = rank;
    }

    private VipCustomer() {
        super(null, null, null);
    }

    public static final String NAME_CUSTOMER_NO = "客户编号";

    private static void doNothingByVipCustomer() {
        //do nothing
    }

    public static void doSomethingByVipCustomer() {
        //do something
    }

}
