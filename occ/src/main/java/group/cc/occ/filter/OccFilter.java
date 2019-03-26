package group.cc.occ.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import group.cc.core.ResultGenerator;
import group.cc.occ.model.dto.LoginUserDto;
import group.cc.occ.util.CusAccessObjectUtil;
import group.cc.occ.util.InitUtil;
import group.cc.occ.util.RedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private Log log = LogFactory.getLog(OccFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LoginUserDto login = RedisUtil.getLoginInfo(redisTemplate, request);

        String URL = request.getServletPath();
        List url = InitUtil.getUrl();

        log.info("Visit IP[OccFilter]: " + CusAccessObjectUtil.getIpAddress(request));

        if(url.contains(URL) || login != null){
            log.info("申请访问：" + URL);
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            log.info("没有登录：" + URL);
            servletRequest.getRequestDispatcher("/occ/user/unLogin").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
