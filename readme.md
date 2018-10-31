## cloud-calendar [![Build Status](https://travis-ci.org/yuanliccc/cloud-calendar.svg?branch=master)](https://travis-ci.org/yuanliccc/cloud-calendar) [![codecov.io](https://codecov.io/gh/yuanliccc/cloud-calendar/branch/master/graphs/badge.svg?branch=master)](https://codecov.io/gh/yuanliccc/cloud-calendar?branch=master) [![GitHub version](https://badge.fury.io/gh/yuanliccc%2Fcloud-calendar.svg)](https://badge.fury.io/gh/yuanliccc%2Fcloud-calendar)

云日历

### 项目结构

- org - 组织机构
- sso - 单点登录
- cg   - 代码生成
- bms - 后台管理
- pcc - 个人日历
- occ - 机构日历
- df   - 动态表单

### 如何基于基础代码开发

1、创建 module

maven 项目创建 module。

2、修改 module 的 pom.xml

1）依赖 module group.cc.core，此模块是项目的核心，包括了mvc 配置，mybatis 配置，及基本数据类型的封装，如 `group.cc.core.Result` 封装了所有 API 响应结果。另外请不要添加 mybatis、spring 、SpringBoot（starter）、fastjson 等依赖到当前 module 中，因为这些依赖已在 core 中被添加。

```xml
<dependency>
    <groupId>group.calendar</groupId>
    <artifactId>core</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

2）如果还需要代码生成（Controller、Service、Impl、Mapper），请添加依赖（[如何使用代码生成](./cg/readme.md)）：

```xml
<dependency>
    <groupId>group.calendar</groupId>
    <artifactId>cg</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

3）修改打包方式为 war

```xml
<packaging>war</packaging>
```

3、请在 resources 中添加相关的配置文件:

application.properties

```properties
spring.profiles.active=dev
# 所有环境通用的配置，放在这里

# 404 交给异常处理器处理
spring.mvc.throw-exception-if-no-handler-found=true
....
```

application-dev.properties

```properties
# 开发环境配置
# 数据源配置，请修改为你项目的实际配置
spring.datasource.url=jdbc:mysql://.....
spring.datasource.username=root
spring.datasource.password=rootroot
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
.....
```

4、优先书写当前 module 的启动类：

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(value = "group.cc.bms.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

