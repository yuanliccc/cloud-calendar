package group.cc.occ;

import group.cc.bms.service.ChatService;
import group.cc.bms.webscoket.WebNoticeSocketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

import java.awt.*;

/**
 * Created by xiaoming on 2019/1/2.
 */
@SpringBootApplication(scanBasePackages = {"group.cc.bms.*","group.cc.occ.*"})
@ServletComponentScan
@EnableCaching
@EnableSwagger2
@MapperScan(value = {"group.cc.occ.dao","group.cc.bms.dao"})
public class OccApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(OccApplication.class, args);
        WebNoticeSocketService.setApplicationContext(configurableApplicationContext);
    }
}
