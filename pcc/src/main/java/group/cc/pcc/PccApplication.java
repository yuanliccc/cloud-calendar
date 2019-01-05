package group.cc.pcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(value = "group.cc.pcc.dao")
@org.mybatis.spring.annotation.MapperScan("group.cc.pcc.dao")
public class PccApplication {

    public static void main(String[] args) {

        SpringApplication.run(PccApplication.class, args);
    }
}
