package com.rx.demo.conf;

import com.rx.demo.constant.RESOURCES_TYPE;
import com.rx.demo.service.ResourcesService;
import com.rx.demo.shiro.filter.CustomLogoutFilter;
import com.rx.demo.shiro.filter.CustomRolesAuthorizationFilter;
import com.rx.demo.shiro.realm.CustomRealm;
import com.rx.demo.shiro.session.CustomSessionManage;
import com.rx.demo.utils.Utils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(ResourcesService resourcesService, SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置登陆页
        shiroFilterFactoryBean.setLoginUrl("/login");

        Map<String, Filter> filtersMap = new LinkedHashMap<>();

        filtersMap.put("roles", new CustomRolesAuthorizationFilter());
        filtersMap.put("logout", new CustomLogoutFilter());

        shiroFilterFactoryBean.setFilters(filtersMap);

        // 权限控制map
        Map<String, String> map = new LinkedHashMap<>();

        //静态资源不拦截
        map.put("/static/**", "anon");
        map.put("/files/**", "anon");

        //登陆页面不拦截
        map.put("/login", "anon");

        map.put("/", "anon");

        map.put("/app/user/customer_service_url", "anon");

        /**
         * 退出路径
         */
        map.put("/user/logout", "logout");


        map.put("/jsons/**", "anon");

        /**
         * 加载免登陆菜单
         */
        resourcesService.findAllByType(RESOURCES_TYPE.PUBLIC_RESOURCES).forEach(e -> {
            String url = e.getUrl();
            if (!Utils.isEmpty(url)) {
                map.put(e.getUrl(), "anon");
            }
        });

        /**
         * 加载系统授权菜单
         */
        map.putAll(resourcesService.parseAuth());

        map.put("/**", "roles");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(CustomRealm customRealm, CustomSessionManage customSessionManage) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm);
        securityManager.setSessionManager(customSessionManage);
        return securityManager;
    }

    @Bean
    public CustomSessionManage sessionManager() {
        return new CustomSessionManage();
    }

    /**
     * 身份认证realm
     *
     * @return
     */
    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return customRealm;
    }

    /**
     * 设置加密算法
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        return matcher;
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}
