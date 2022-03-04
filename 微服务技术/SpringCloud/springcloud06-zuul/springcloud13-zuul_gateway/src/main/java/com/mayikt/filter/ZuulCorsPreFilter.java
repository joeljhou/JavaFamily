//package com.mayikt.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class ZuulCorsPreFilter extends ZuulFilter {
//
//     /*
//        过滤器类型
//        pre：可以在请求被路由之前调用
//        route：在路由请求时候被调用
//        post：在route和error过滤器之后被调用
//        error：处理请求时发生错误时被调用
//        */
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    // 过滤器的执行顺序。当请求在一个阶段的时候存在多个多个过滤器时，需要根据该方法的返回值依次执行
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    // 判断过滤器是否生效
//    @Override
//    public boolean shouldFilter() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        //只过滤OPTIONS 请求
//        if(request.getMethod().equals(RequestMethod.OPTIONS.name())){
//            return true;
//        }
//        return false;
//    }
//
//    //主要的处理逻辑的地方，我们做权限控制、日志等都是在这里
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletResponse response = ctx.getResponse();
//        HttpServletRequest request = ctx.getRequest();
//        //指定允许其他域名访问
//        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//        //是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
//        response.setHeader("Access-Control-Allow-Credentials","true");
//        //允许的请求头字段
//        response.setHeader("Access-Control-Allow-Headers","authorization, x-requested-with, content-type");
//        //允许的请求类型
//        response.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,PUT,PATCH");
//        response.setHeader("Access-Control-Expose-Headers","X-forwared-port, X-forwarded-host");
//        response.setHeader("Vary","Origin,Access-Control-Request-Method,Access-Control-Request-Headers");
//        //不再路由
//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(200);
//        return null;
//    }
//
//}
