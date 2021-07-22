//package com.mayikt.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author 周宇
// * @create 2021-07-23 0:57
// */
////@Component
//public class ZuulTokenFilter extends ZuulFilter {
//
//    @Override
//    public Object run() throws ZuulException {
//        // 获取上下文
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = currentContext.getRequest();
//        String userToken = request.getParameter("userToken");
//        if (StringUtils.isEmpty(userToken)) {
//            currentContext.setSendZuulResponse(false);
//            currentContext.setResponseStatusCode(401);
//            currentContext.setResponseBody("userToken is null");
//            return null;
//        }
//        // 否则正常执行业务逻辑.....
//        return null;
//    }
//
//    // 判断过滤器是否生效
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    // 过滤器的执行顺序。当请求在一个阶段的时候存在多个多个过滤器时，需要根据该方法的返回值依次执行
//    @Override
//    public int filterOrder() {
//        return 0;
//    }
//
//    // 过滤器类型 pre 表示在 请求之前进行拦截
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//}
