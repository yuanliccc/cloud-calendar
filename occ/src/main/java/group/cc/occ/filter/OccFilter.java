package group.cc.occ.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.util.CusAccessObjectUtil;
import group.cc.occ.util.InitUtil;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Administrator on 2019/2/28.
 */
@WebFilter(filterName = "OccFilter", urlPatterns = "/*")
public class OccFilter implements Filter {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LoginUserDto login = (LoginUserDto)redisTemplate.opsForValue().get("userInfo" + CusAccessObjectUtil.getIpAddress(request));

        String URL = request.getServletPath();
        List url = InitUtil.getUrl();

        if(url.contains(URL) || login != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            servletRequest.getRequestDispatcher("/occ/user/unLogin").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
