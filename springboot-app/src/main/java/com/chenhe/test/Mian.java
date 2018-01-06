package com.chenhe.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhe on 2017/12/29.
 */
public class Mian {

    public static void main(String[] args) {
        List<BaseEntity> baseEntities = new ArrayList<>();

        while (true){
            BaseEntity baseEntity = new BaseEntity();
            baseEntities.add(baseEntity);
            if (baseEntities.size() == 10000){
                baseEntities.clear();
            }
        }
    }
}
