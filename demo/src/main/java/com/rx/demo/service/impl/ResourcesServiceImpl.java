package com.rx.demo.service.impl;

import com.rx.demo.constant.CONSTANT;
import com.rx.demo.constant.RESOURCES_TYPE;
import com.rx.demo.domain.Resources;
import com.rx.demo.repositpry.ResourcesRepository;
import com.rx.demo.service.ResourcesService;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.*;


@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources, ResourcesRepository> implements ResourcesService {

    private ResourcesRepository resourcesRepository;

    @Autowired
    @Override
    public void setRepository(ResourcesRepository repository) {
        this.resourcesRepository = repository;
        this.baseRepository = repository;
    }

    @Override
    public List<Resources> findAllByDisplay(int display) {
        return resourcesRepository.findAllByDisplay(display);
    }

    @Override
    public List<Resources> findAllByType(RESOURCES_TYPE resources_type) {
        return resourcesRepository.findAllByType(resources_type.getType());
    }

    @Override
    public Map<String, String> parseAuth() {

        List<Resources> all = new ArrayList<>();
        all.addAll(this.findAllByType(RESOURCES_TYPE.ORDINARY_RESOURCE));
        all.addAll(this.findAllByType(RESOURCES_TYPE.SYSTEM_RESOURCES));

        Map<String, String> result = new HashMap<>();

        all.forEach(r -> {
            String sql = "SELECT role.permission FROM " + CONSTANT.TABLE_PREFIX + "role AS role WHERE role.sid IN (SELECT re.role_sid FROM " + CONSTANT.TABLE_PREFIX + "role_resources as re WHERE re.resources_sid = :resourcesSid)";

            Map<String, Object> param = new HashMap<>();
            param.put("resourcesSid", r.getSid());
            Query q = this.runSqlForQuery(sql, param);

            String url = r.getUrl();

            if (!Utils.isNull(url)) {
                StringBuilder permission = new StringBuilder("roles[");
                for (Object o : q.getResultList()) {
                    permission.append(o).append(",");
                }
                permission.append("]");
                result.put(url, permission.toString());
            }
        });

        return result;
    }

    @Override
    public List<Resources> parseResourceTree(List<Resources> resources) {
        List<Resources> first = new ArrayList<>();

        for (Resources r : resources) {
            if (Utils.isNull(r.getParentSid()) || r.getParentSid().equals(-1L)) {
                first.add(r);
            }
        }

        resources.removeAll(first);
        for (Resources r : first) {
            r.setChildren(getChildren(r, resources));
        }

        Collections.sort(first);

        return first;
    }


    private List<Resources> getChildren(Resources parent, List<Resources> resources) {

        List<Resources> children = new ArrayList<>();

        for (Resources r : resources) {

            if (r.getParentSid().equals(parent.getSid())) {
                children.add(r);
                r.setChildren(getChildren(r, resources));
            }

        }

        Collections.sort(children);
        return children;
    }
}
