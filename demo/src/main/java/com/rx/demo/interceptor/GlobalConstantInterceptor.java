package com.rx.demo.interceptor;

import com.rx.demo.constant.RESOURCES_TYPE;
import com.rx.demo.domain.Resources;
import com.rx.demo.service.ResourcesService;
import com.rx.demo.utils.ShiroUtils;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashSet;
import java.util.List;


@Component
public class GlobalConstantInterceptor implements HandlerInterceptor {

    private final static LinkedHashSet<Resources> menuTree = new LinkedHashSet<>();
    private static ResourcesService resourceService;

    @Autowired
    public void setResourceService(ResourcesService resourceService) {
        GlobalConstantInterceptor.resourceService = resourceService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (Utils.isEmpty(menuTree)) {
            loadMenuTree();
        }
        request.setAttribute("MENU_TREE", menuTree);
        request.setAttribute("CURRENT_USER", ShiroUtils.getCurrentUser());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 加载所有有效资源并组装成树结构
     */
    private static void loadMenuTree() {
        menuTree.clear();
        List<Resources> resources = resourceService.findAllByDisplay(RESOURCES_TYPE.ORDINARY_RESOURCE.getType());
        List<Resources> parseResourceTree = resourceService.parseResourceTree(resources);
        menuTree.addAll(parseResourceTree);
    }


}
