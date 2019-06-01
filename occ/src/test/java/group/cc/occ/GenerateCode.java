package group.cc.occ;

import group.cc.cg.VueGenerator;
import group.cc.cg.XMCodeGenerator;

import java.util.*;

public class GenerateCode {

    public static void main(String[] args) {
        //XMCodeGenerator.generate();
        VueGenerator.generate();
    }

    public static void test(){
        List<Integer> list1 = new ArrayList<>();

        for (int i = 0; i < 5; i++) list1.add(i);

        Stack<Integer> stack = new Stack<>();

        stack.addAll(list1);

        System.out.println(stack);
    }

    private static  <T> T nullToDefault(Object aim, T defaultValue) {
        if(defaultValue instanceof Integer && aim != null){
            Integer t = Integer.parseInt(aim + "");
            return (T)t;
        }else if(defaultValue instanceof Integer && aim == null){
            Integer t = Integer.parseInt(defaultValue + "");
            return (T)t;
        }

        return aim == null ? defaultValue : (T)aim;
    }

}
