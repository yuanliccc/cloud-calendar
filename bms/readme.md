#bms

私信功能
在数据库建表
CREATE TABLE `chat` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `sendUserId` int(11) DEFAULT NULL,
  `receiveUserId` int(11) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `hadSeen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;



启动Application时，要在自身的Application注入服务，因为在WebNoticeService中无法通过自动注入依赖

例如：
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


--------------------------------------------------------

group.cc.bms.webscoket.WebNoticeService

前端webscoket URL： ws://ip:host/notice/:userId
建立起webscoket连接

前端消息推送到后台 会在WebNoticeService的onmessage方法里面获取到消息，解析为Chat对象，然后保存该条消息到数据库
消息格式为JSON （注意：内容要和group.cc.bms.model.Chat相同  否则会无法解析而报错）
然后向用户Id为receiveUserId的用户推送该条Chat


后台向前台推送消息 直接调用WebNoticeService的静态方法sengObject或者sendMessage

