package com.stat.demo.bixin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 资源位挂件详情
 *
 * @author yulu
 */
@Data
public class RoomPendantDetailDTO implements Serializable {

    private static final long serialVersionUID = -1265546855071494287L;
    /**
     * 资源类型
     * 图片 0
     * H5  1
     */
    private int type;

    /**
     * url
     */
    private String url;

    /**
     * scheme
     */
    private String scheme;

}
