package com.rx.demo.shiro.filter;

import com.rx.demo.utils.ShiroUtils;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CustomLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        try {
            ShiroUtils.getCurrentSubject().logout();
        } catch (SessionException ise) {
            ise.printStackTrace();
        }
        issueRedirect(request, response, "/login");
        return false;
    }

}
