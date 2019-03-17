package group.cc.occ.util;

import group.cc.occ.model.dto.LoginUserDto;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/3/14.
 */
public class RedisUtil {
    private static final long LOGIN_TIME = 3600;

    public static void singUp(RedisTemplate<String, Object> redisTemplate, HttpServletRequest request){
        redisTemplate.opsForValue().getOperations().delete("userInfo" + CusAccessObjectUtil.getIpAddress(request));
    }

    public static void login(RedisTemplate<String, Object> redisTemplate, HttpServletRequest request, LoginUserDto loginUserDto){
        redisTemplate.opsForValue().set("userInfo" + CusAccessObjectUtil.getIpAddress(request), loginUserDto, LOGIN_TIME, TimeUnit.SECONDS);
    }

    public static void getUserChat(RedisTemplate<String, Object> redisTemplate, LoginUserDto loginUserDto){
        redisTemplate.opsForValue().set("userInfo" + loginUserDto.getUser().getId(), loginUserDto, LOGIN_TIME, TimeUnit.SECONDS);
    }

    public static LoginUserDto getLoginInfo(RedisTemplate<String, Object> redisTemplate, HttpServletRequest request){
        LoginUserDto login = (LoginUserDto)redisTemplate.opsForValue().get("userInfo" + CusAccessObjectUtil.getIpAddress(request));

        return login;
    }
}
