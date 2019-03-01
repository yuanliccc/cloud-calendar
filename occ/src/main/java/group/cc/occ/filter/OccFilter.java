package group.cc.occ.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import group.cc.occ.util.InitUtil;

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
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(true);

        String URL = request.getServletPath();
        List url = InitUtil.getUrl();

        filterChain.doFilter(servletRequest, servletResponse);
       /* if(url.contains(URL) || session.getAttribute("userInfo") != null){
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            JSONObject json = new JSONObject();
            json.put("code","401");
            json.put("message", "No Login");
            json.put("stats","Fail");

            response.getWriter().write(json.toString());
            response.setHeader("content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().flush();
            response.getWriter().close();
            response.setStatus(401);
        }*/
    }

    @Override
    public void destroy() {

    }
}
