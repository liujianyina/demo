package com.rx.demo.service.impl;


import com.rx.demo.domain.BaseDomain;
import com.rx.demo.repositpry.BaseRepository;
import com.rx.demo.service.BaseService;
import com.rx.demo.utils.Utils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;



@SuppressWarnings("all")
@Transactional
public abstract class BaseServiceImpl<T extends BaseDomain, R extends BaseRepository<T>> implements BaseService<T> {


    private static final String REPO_CLS_NAME = BaseRepository.class.getName();

    /**
     * 更新记录状态 SQL
     */
    private String SQL_UPDATE_STATUS_SINGLE = "UPDATE %s SET status = :status where sid = :sid";
    private String SQL_UPDATE_STATUS_BATCH = "UPDATE %s SET status = :status where sid in (:sids)";

    /**
     * 查询单个实体
     */
    private String SQL_QUERY_SINGLE = "SELECT * FROM %s WHERE sid = :sid";


    /**
     * 实体表名
     */
    private String TABLE_NAME;


    BaseRepository<T> baseRepository;

    public abstract void setRepository(R repository);

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        Type[] types = baseRepository.getClass().getGenericInterfaces();
        if (null != types && types.length > 0) {
            Type type = types[0];
            try {
                Class<?> cls = Class.forName(type.getTypeName());
                types = cls.getGenericInterfaces();
                if (null != types && types.length > 0) {
                    type = types[0];
                    String name = type.getTypeName().replace(REPO_CLS_NAME + "<", "").replace(">", "");
                    cls = Class.forName(name);
                    Method method = cls.getMethod("getTableName");
                    if (null != method) {
                        TABLE_NAME = String.valueOf(method.invoke(cls.newInstance()));
                        SQL_UPDATE_STATUS_SINGLE = String.format(SQL_UPDATE_STATUS_SINGLE, TABLE_NAME);
                        SQL_UPDATE_STATUS_BATCH = String.format(SQL_UPDATE_STATUS_BATCH, TABLE_NAME);
                        SQL_QUERY_SINGLE = String.format(SQL_QUERY_SINGLE, TABLE_NAME);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public T findOne(Long sid) {
        try {
            return baseRepository.findById(sid).get();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    public List<T> save(List<T> entities) {
        return baseRepository.saveAll(entities);
    }

    @Override
    public void delete(Long sid) {
        baseRepository.deleteById(sid);
    }

    @Override
    public void delete(List<Long> sids) {
        sids.forEach(this::delete);
    }


    @Override
    public void remove(Long sid) {
        setStatus(-1, sid);
    }

    @Override
    public void remove(List<Long> sids) {
        setStatus(-1, sids);
    }

    @Override
    public void remove(Long[] sids) {
        setStatus(-1, sids);
    }

    @Override
    public void disable(Long sid) {
        setStatus(0, sid);
    }

    @Override
    public void disable(List<Long> sids) {
        setStatus(0, sids);
    }

    @Override
    public void disable(Long[] sids) {
        setStatus(0, sids);
    }

    @Override
    public void enable(Long sid) {
        setStatus(1, sid);
    }

    @Override
    public void enable(List<Long> sids) {
        setStatus(1, sids);
    }

    @Override
    public void enable(Long[] sids) {
        setStatus(1, sids);
    }

    @Override
    public void updateStatus(Integer status, Long sid) {
        setStatus(status, sid);
    }

    @Override
    public void updateStatus(Integer status, List<Long> sids) {
        setStatus(status, sids);
    }

    @Override
    public void updateStatus(Integer status, Long[] sids) {
        setStatus(status, sids);
    }

    @Override
    public List<T> findAllByStatus(Integer status) {
        return baseRepository.findAll((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("status"), status);
        });
    }

    @Override
    public List<T> findAllByStatus(Integer[] status) {
        return baseRepository.findAll((root, query, criteriaBuilder) -> {
            return root.get("status").in(status);
        });
    }

    @Override
    public List<T> findAllByNotStatus(Integer status) {
        return baseRepository.findAll((root, query, criteriaBuilder) -> {
            return criteriaBuilder.notEqual(root.get("status"), status);
        });
    }

    @Override
    public List<T> findAllByNotStatus(Integer[] status) {
        return baseRepository.findAll((root, query, criteriaBuilder) -> {
            return criteriaBuilder.not(root.get("status").in(status));
        });
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> findBySids(List<Long> sids) {
        return baseRepository.findAll((root, query, criteriaBuilder) -> {
            return root.get("sid").in(sids);
        });
    }

    /**
     * 更新记录状态
     *
     * @param status
     * @param sid
     */
    private void setStatus(Integer status, Object sid) {
        if (!Utils.isEmpty(TABLE_NAME)) {
            Query query = null;
            if (sid instanceof Long[] || sid instanceof List<?>) {
                query = em.createNativeQuery(SQL_UPDATE_STATUS_BATCH);

                List<String> sids = new ArrayList<>();
                if (sid instanceof Long[]) {
                    for (Long s : (Long[]) sid) {
                        sids.add(String.valueOf(s));
                    }
                } else {
                    for (Object s : (List<?>) sid) {
                        sids.add(String.valueOf(s));
                    }
                }
                query.setParameter("sids", sids);
            } else {
                query = em.createNativeQuery(SQL_UPDATE_STATUS_SINGLE);
                query.setParameter("sid", sid);
            }
            query.setParameter("status", status);
            query.executeUpdate();
        }
    }

    @Override
    public Query runSqlForUpdateOrDelete(String sql, Map<String, Object> map) {
        Query query = em.createNativeQuery(sql);
        map.forEach((k, v) -> {
            query.setParameter(k, v);
        });
        query.executeUpdate();
        return query;
    }

    @Override
    public Query runSqlForQuery(String sql, Map<String, Object> map) {
        Query query = em.createNativeQuery(sql);
        map.forEach((k, v) -> {
            query.setParameter(k, v);
        });
        return query;
    }

    @Override
    public Query runSqlForQuery(String sql, Map<String, Object> map, Class clz) {
        Query query = em.createNativeQuery(sql, clz);
        map.forEach((k, v) -> {
            query.setParameter(k, v);
        });
        return query;
    }


}
