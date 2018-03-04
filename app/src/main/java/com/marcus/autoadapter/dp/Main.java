package com.marcus.autoadapter.dp;

/**
 * Created by Marcus on 2018/3/4.
 * <p>
 * Android dp适配dimen生成类
 */

public class Main {

    public static void main(String arg[]) {
        DpUntil dpUntil = new DpUntil();
        if (arg != null && arg.length > 0) {
            String parameter = arg[0];
            if (parameter != null && !parameter.equals("")) {
                dpUntil.start(Integer.valueOf(parameter));
            } else {
                dpUntil.start();
            }
        } else {
            dpUntil.start();
        }
    }

}
