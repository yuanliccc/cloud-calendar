package group.cc.occ;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateCode {

    public static void main(String[] args) {
        //CodeGenerator.generate();
        //VueGenerator.generate();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++){
            list.add(i + "aaaa");
        }

        list = list.stream().sorted((String b,String a) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println(list);

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
