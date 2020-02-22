package com.rx.demo.task;

import com.rx.demo.domain.KPoint;
import com.rx.demo.service.KPointService;
import com.rx.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealKPoint {

    private static KPointService kPointService;

    @Autowired
    public void setValueService(KPointService kPointService) {
        DealKPoint.kPointService = kPointService;
    }

    public static void dealValue(Float min,Float max,Long productSid){

        new Thread(() -> {

            for (int i = 0; i < 1000; i ++){
                KPoint value = Utils.createKPoint(min,max);
                value.setProductSid(productSid);
                kPointService.save(value);
            }

        }).start();

    }


}
