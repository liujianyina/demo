package com.rx.demo.service;

import com.rx.demo.constant.RESOURCES_TYPE;
import com.rx.demo.domain.Resources;

import java.util.List;
import java.util.Map;


public interface ResourcesService extends BaseService<Resources> {

    /**
     * 将给定的资源列表组装为树型
     *
     * @param resources
     * @return
     */
    List<Resources> parseResourceTree(List<Resources> resources);

    List<Resources> findAllByDisplay(int display);

    List<Resources> findAllByType(RESOURCES_TYPE resources_type);

    /**
     * 组装shiro权限
     *
     * @return
     */
    Map<String, String> parseAuth();

}
