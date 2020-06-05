package com.digimenu.main.controller.filter;

import org.springframework.security.web.util.UrlUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileSizeFilter implements Filter {
    public FileSizeFilter() {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("in filter");
       try{
           chain.doFilter(req,resp);
       }catch (MaxUploadSizeExceededException e){
            handle((HttpServletRequest) req, (HttpServletResponse) resp);
       }catch (ServletException e) {
           //bu if kontrolünde yakalıyor yukardaki catchde değil ! o yüzden silme
           if(e.getRootCause() instanceof MaxUploadSizeExceededException) {
               handle((HttpServletRequest) req, (HttpServletResponse) resp);
           } else {
               throw e;
           }
       }
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {

        String redirect = UrlUtils.buildFullRequestUrl(request) + "?bigfile";
        System.out.println(redirect);
        response.sendRedirect(redirect);
    }
    public void init(FilterConfig config) throws ServletException {

    }

}
