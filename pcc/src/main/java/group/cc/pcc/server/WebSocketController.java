package group.cc.pcc.server;

import group.cc.core.Result;
import group.cc.core.ResultGenerator;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author yuanli
 * @date 2019/01/15
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/pcc/chat/api")
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private WebSocketServer webSocketServer;

    @ApiOperation("发送文本信息")
    @PostMapping("/sendText")
    public Result sendText(@RequestParam Integer toUserId, @RequestParam String textMessage) {
        try {
            webSocketServer.sendMessage(textMessage, toUserId);
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("发送聊天信息失败", e);

            return ResultGenerator.genFailResult("发送聊天信息失败！");
        }
        return ResultGenerator.genSuccessResult();
    }



}
