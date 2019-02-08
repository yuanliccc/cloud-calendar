package group.cc.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(value = "group.cc.df.dao")
public class DfApplication {
    public static void main(String[] args) {
        SpringApplication.run(DfApplication.class, args);
    }
}
