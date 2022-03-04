package com.mayiky.mapper.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;

import java.io.Serializable;

/**
 * @author 周宇
 * @create 2021-07-29 23:20
 */
@Data
public class GatewayHandlerEntity implements Serializable,Cloneable {
    /**
     * 主键id
     */
    private Insert id;
    /**
     * handler名称
     */
    private String handlerName;
    /**
     * handler主键id
     */
    private String handlerId;
    /**
     *  下一个handler
     */
    private String nextHandlerId;

}
