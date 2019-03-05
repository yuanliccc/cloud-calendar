package group.cc.cg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;


/**
 * Created by Administrator on 2019/3/5.
 */
public class VueGenerator {
    private static final String classpath = CodeGenerator.class.getResource("").getPath();

    private static final String TEMPLATE_FILE_PATH = classpath.substring(0, classpath.length() - 13)
            + "/template/vue";//模板位置

    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date

    private static final String DEFAULT_FILE = "code-generator.properties";

    private static final  String DISPLAY = "Display.vue";

    private static final  String ROUTE = "Route.js";

    private static final  String FORM = "Form.vue";

    private static final  String LIST = "List.vue";

    private static final  String DISPLAYFTL = "display.ftl";

    private static final  String ROUTEFTL = "route.ftl";

    private static final  String FORMFTL = "form.ftl";

    private static final  String LISTFTL = "list.ftl";

    public static void generate() {
        Map<String, String> params = VueParameterReader.read(DEFAULT_FILE);
        Map<String, Object> detailParams = changeMap(params);
        generator(detailParams, DISPLAY, DISPLAYFTL);
        generator(detailParams, ROUTE, ROUTEFTL);
        generator(detailParams, FORM, FORMFTL);
        generator(detailParams, LIST, LISTFTL);
    }

    private static Map<String, Object> changeMap(Map<String, String> params){
        Map<String, Object> theParams = new HashMap<>();

        theParams.put("vueModuleChineseName", params.get("vue_chinese_name"));
        theParams.put("vuePath", params.get("vue_path"));
        theParams.put("vueModuleNameLowerCamel", params.get("vue_module_name"));
        theParams.put("vueModuleNameUpperCamel", firstCharUpper(params.get("vue_module_name")));

        String[] fields = params.get("vue_module_filed").replace(" ", "").split(",");
        String[] disFields = params.get("vue_module_filed_dis").replace(" ", "").split(",");
        String[] disFieldsChinese = params.get("vue_module_filed_dis_chinese").replace(" ", "").split(",");
        List<Map<String, String>> disFieldsChineseMap = new ArrayList<>();

        for (int i = 0; i < disFields.length; i++){
            Map<String, String> tem = new HashMap<>();
            tem.put("chineseName", disFieldsChinese[i]);
            tem.put("field", disFields[i]);
            disFieldsChineseMap.add(tem);
        }

        theParams.put("disFields", disFieldsChineseMap);
        theParams.put("fields", fields);

        return theParams;
    }

    private static void generateDisplay(Map<String, Object> theParams){
        Configuration cfg = getConfig();

        File file = new File( theParams.get("vuePath") + "/" + theParams.get("vueModuleNameLowerCamel")
                + "/" + theParams.get("vueModuleNameLowerCamel") + "Display.vue");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            cfg.getTemplate("display.ftl").process(theParams, new FileWriter(file));
            System.out.println("生成" + theParams.get("vueModuleNameLowerCamel") + "Display.vue成功");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param genType: 生成的文件类型 Display.vue, List.vue...
     * @param  genFileName: 生成文件的ftl文件
     * */
    private static void generator(Map<String, Object> theParams, String genType, String genFileName){
        Configuration cfg = getConfig();

        File file = new File( theParams.get("vuePath") + "/" + theParams.get("vueModuleNameLowerCamel")
                + "/" + theParams.get("vueModuleNameLowerCamel") + genType);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            cfg.getTemplate(genFileName).process(theParams, new FileWriter(file));
            System.out.println("生成" + theParams.get("vueModuleNameLowerCamel") + genType + "成功");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private static Configuration getConfig(){
        Configuration cfg = new Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        try {
            cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String firstCharUpper(String text){
        String temp = (text.charAt(0) + "").toUpperCase() + text.substring(1);
        return temp;
    }
}
