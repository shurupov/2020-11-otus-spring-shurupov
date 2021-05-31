package ru.otus.shurupov.jointpurchase.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class JwtAuthenticationFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        Map<String, String> zuulRequestHeaders = ctx.getZuulRequestHeaders();
        HttpServletRequest request = ctx.getRequest();
        ctx.addZuulRequestHeader("Authorization", request.getHeader("Authorization"));
        ctx.addZuulRequestHeader("Username", "saveduser");
//        zuulRequestHeaders.put("Authorization", request.getHeader("Authorization"));
        return null;
    }
}
