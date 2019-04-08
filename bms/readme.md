##bms

####私信功能
#####在数据库建表
`CREATE TABLE chat(`    
&nbsp;&nbsp;&nbsp;&nbsp;`id int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,`   
&nbsp;&nbsp;&nbsp;&nbsp;`orgId int(11) DEFAULT NULL,`   
&nbsp;&nbsp;&nbsp;&nbsp;`sendUserId int(11) DEFAULT NULL,`    
&nbsp;&nbsp;&nbsp;&nbsp;`receiveUserId int(11) DEFAULT NULL,`   
&nbsp;&nbsp;&nbsp;&nbsp;`sendTime datetime DEFAULT NULL,`   
&nbsp;&nbsp;&nbsp;&nbsp;`content varchar(255) DEFAULT NULL,`      
&nbsp;&nbsp;&nbsp;&nbsp;`type varchar(255) DEFAULT NULL,`   
&nbsp;&nbsp;&nbsp;&nbsp;`hadSeen varchar(255) DEFAULT NULL,`  
&nbsp;&nbsp;&nbsp;&nbsp;`PRIMARY KEY (id)`    
`) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;`   



####启动Application时，要在自身的Application注入服务，因为在WebNoticeService中无法通过自动注入依赖

####例如：
`@SpringBootApplication(scanBasePackages = {"group.cc.bms.*","group.cc.occ.*"})`    
`@ServletComponentScan` 
`@EnableCaching`    
`@EnableSwagger2`   
`@MapperScan(value = {"group.cc.occ.dao","group.cc.bms.dao"})`  
`public class OccApplication {`     
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`public static void main(String[] args){`   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(OccApplication.class, args);`    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`WebNoticeSocketService.setApplicationContext(configurableApplicationContext);`     
&nbsp;&nbsp;&nbsp;&nbsp;`}`     
`}`



######-------------------------------------------------------------------------------------------------------

####group.cc.bms.webscoket.WebNoticeService

前端webscoket URL： ws://ip:host/notice/:userId    
建立起webscoket连接  

前端消息推送到后台，会在**WebNoticeService**的**onmessage**方法里面获取到消息，解析为**Chat**对象，然后保存该条消息到数据库
消息格式为**JSON** （**注意**：内容要和group.cc.bms.model.Chat相同  否则会无法解析而报错）
然后向用户Id为receiveUserId的用户推送该条Chat


后台向前台推送消息 直接调用**WebNoticeService**的静态方法**sengObject**或者**sendMessage**

