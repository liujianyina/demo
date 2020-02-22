package com.rx.demo.domain;

import java.io.Serializable;
import java.util.Map;


public interface BaseDomainInterface extends Serializable {

    /**
     * 实体转换为 Map
     *
     * @return
     */
    Map<String, Object> toMap();

    /**
     * 实体转换为 Map
     *
     * @param fields 指定包含的字段名称数组
     * @return
     */
    Map<String, Object> toMapWithInclude(String[] fields);

    /**
     * 实体转换为 Map
     *
     * @param fields 指定排除的字段名称数组
     * @return
     */
    Map<String, Object> toMapWithExclude(String[] fields);


}
