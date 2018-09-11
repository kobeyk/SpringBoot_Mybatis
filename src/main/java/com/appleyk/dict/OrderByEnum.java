package com.appleyk.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderByEnum {

    DEFAULT(0),
    ID(1),   // 按id排序
    NAME(2); // 按名称排序

    public final int value;
    OrderByEnum(int value){
        this.value = value;
    }

    @JsonCreator
    public static OrderByEnum getEnum(int value){
        for (OrderByEnum orderByEnum : OrderByEnum.values()){
            if (orderByEnum.getValue() == value){
                return orderByEnum;
            }
        }
        return null;
    }

    @JsonValue
    public int getValue(){
        return value;
    }
}
