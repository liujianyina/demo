package com.rx.demo.utils;

import com.rx.demo.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;




@Component
public class ShiroUtils {


    public static Subject getCurrentSubject() {
        return SecurityUtils.getSubject();
    }


    /**
     * 刷新当前用户信息
     */
    public static void refresh(User user) {
        Subject subject = getCurrentSubject();

        PrincipalCollection newPrincipalCollection =
                new SimplePrincipalCollection(user, subject.getPrincipals().getRealmNames().iterator().next());

        subject.runAs(newPrincipalCollection);
    }


    public static User getCurrentUser() {
        return (User) getCurrentSubject().getPrincipal();
    }

    public static Session getSession() {
        return getCurrentSubject().getSession();
    }


}
