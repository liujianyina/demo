package com.rx.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rx.demo.constant.CONSTANT;
import com.rx.demo.domain.BaseDomain;
import com.rx.demo.domain.BaseDomainInterface;
import com.rx.demo.domain.KPoint;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.*;


@Component
public class Utils {

    //c4ca86dead4518ac4fd6e30172db3d9e
//    public static void main(String[] args) {
//        System.out.println(getMd5("system", "system"));
//    }

    public static JSONObject getRequestBody(String requestBody) {
        return JSONObject.parseObject(requestBody);
    }

    /**
     * 获取字符串的加密串
     *
     * @param source
     * @param salt
     * @return
     */
    public static String getMd5(String source, String salt) {
        Md5Hash md5Hash = new Md5Hash(source, ByteSource.Util.bytes(salt));
        return md5Hash.toString();
    }


    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || str.trim().length() == 0;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.size() == 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return isNull(array) || array.length == 0;
    }


    /**
     * 将对象反射为 Map<String, Object>
     *
     * @param o
     * @param includeFields
     * @param excludeFields
     * @return
     */
    public static Map<String, Object> toMap(BaseDomainInterface o, String[] includeFields, String[] excludeFields) {
        Map<String, Object> result = new HashMap<>();

        if (Utils.isNull(includeFields)) {
            includeFields = new String[]{};
        }

        if (Utils.isNull(excludeFields)) {
            excludeFields = new String[]{};
        }

        Class<?> cls = o.getClass();
        while (!cls.getName().equals(BaseDomain.class.getName())) {
            getFieldValue(o, cls, result, includeFields, excludeFields);
            cls = cls.getSuperclass();
        }

        if (cls.getName().equals(BaseDomain.class.getName())) {
            getFieldValue(o, cls, result, includeFields, excludeFields);
        }

        return result;
    }

    private static void getFieldValue(Object from, Class<?> cls, Map<String, Object> result, String[] includeFields, String[] excludeFields) {
        List<String> iFields = Arrays.asList(includeFields);
        List<String> eFields = Arrays.asList(excludeFields);

        for (Field field : cls.getDeclaredFields()) {
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }

            if ((!Utils.isEmpty(iFields) && !iFields.contains(field.getName())) || (!Utils.isEmpty(eFields) && eFields.contains(field.getName()))) {
                continue;
            }

            boolean isIgnore = false;
            for (Annotation anno : field.getAnnotations()) {
                if (anno instanceof JsonIgnore) {
                    isIgnore = true;
                    break;
                }
            }

            if (isIgnore) {
                continue;
            }

            field.setAccessible(true);
            try {
                Object obj = field.get(from);
                if (obj instanceof BaseDomainInterface) {
                    result.put(field.getName(), ((BaseDomainInterface) obj).toMap());
                } else if (obj instanceof Collection) {
                    Collection<Object> array = new ArrayList<>();
                    for (Object o : (Collection<?>) obj) {
                        if (o instanceof BaseDomainInterface) {
                            array.add(((BaseDomainInterface) o).toMap());
                        } else {
                            array.add(o);
                        }
                    }
                    result.put(field.getName(), array);
                } else {
                    result.put(field.getName(), obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Float randomFloat(float min, float max) {
        return min + (max - min) * new Random().nextFloat();
    }

    private static String randomFlag(){
        return new Random().nextInt() % 2 == 0 ? "+" : "-";
    }

    public static KPoint createKPoint(float min, float max){
        KPoint kPoint = new KPoint();
        Float open = randomFloat(min,max);
        Float close = randomFloat(min,max);
        Float low = randomFloat(min,max);
        Float height = randomFloat(min,max);

        Float[] floats = new Float[]{open,close,low,height};
        Arrays.sort(floats);
        kPoint.setOpen(open);
        kPoint.setClose(close);
        kPoint.setLow(floats[0]);
        kPoint.setHeight(floats[3]);
        kPoint.setFlag(randomFlag());

        return kPoint;
    }

    public static String getCode() {
        return RandomStringUtils.randomNumeric(CONSTANT.DEFAULT_CODE_LENGTH);
    }

    public static Date afterMinute(Integer minute){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,minute);
        return calendar.getTime();
    }

    public static Date initDateByDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date endDateByDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
}
