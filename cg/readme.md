## Code Generator
基于 mybatis-generator 的代码生成，提供通过数据库表生成实体，控制层，业务层，Mapper 代码的 API。

### 注意

1、暂时不支持自定义模板。

2、不支持外键映射实体，如：

```txt
user 表：                   org 表     
id  name  orgId            id  name
```

在生成 user 实体时，不会映射为：`private Org org;`，同时不提倡外键关联，可以定义上面的表，但是不应该将 orgId 设置为外键。

### 如何使用

1、添加依赖

```java
<dependency>
    <groupId>group.calendar</groupId>
    <artifactId>cg</artifactId>
    <version>1.0-SNAPSHOT</version>
    <scope>test</scope>
</dependency>
```

2、配置

在 resources 中添加文件 code-generator.properties，下面是支持的配置项，请不要省略配置项，因为暂时没有进行未配置的默认处理。

```properties
# 将要生成 Controller、Model 等代码的数据库表的名称，以逗号隔开
tables=user,org

# 作者，将在生成的代码的类注释中展示
author=yuanli

# module 名称，如果是子模块，请填写，否则不填写或忽略
module_name=bms

# Java Path
java_path=src/main/java

# resources path
resources_path=src/main/resources

# 代码生成基础包名
base_package=group.cc.bms

# 实体生成包名 = base_package + . + model_package
model_package=model

# mapper 生成包名 = base_package + . + mapper_package
mapper_package=dao

# service 生成包名 = base_package + . + service_package
service_package=service

# serviceImpl 生成包名 = base_package + . + service_imp_package
service_impl_package=service.impl

# controller 生成包名 = base_package + . + controller_package
controller_package=controller

# mapper 实现的接口 = mapper_implements_interface
mapper_implements_interface=group.cc.core.Mapper

# jdbc 配置
jdbc_url=jdbc:mysql://120.77.171.82:3306/test

jdbc_username=root

jdbc_password=rootroot

# jdbc driver class
jdbc_driver_class=com.mysql.jdbc.Driver
```

3、调用

在测试路径下调用代码生成接口，如下：

```java
import group.cc.cg.CodeGenerator;

public class GenerateCode {

    public static void main(String[] args) {

        CodeGenerator.generate();
    }

}
```

执行上述代码，等待代码生成即可。