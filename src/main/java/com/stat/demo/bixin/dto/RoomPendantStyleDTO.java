package com.stat.demo.bixin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏房资源位挂件格式
 *
 * @author yulu
 */
@Data
public class RoomPendantStyleDTO implements Serializable {

    private static final long serialVersionUID = 3256659453201772862L;
    /**
     * 距离顶部距离
     */
    private Double toTop;

    /**
     * 距离左侧距离
     */
    private Double toLeft;

    /**
     * 挂件宽度
     */
    private Double width;

    /**
     * 挂件宽高比
     */
    private Double aspectRatio;

    /**
     * 轮播间隔 秒
     */
    private Integer carouselInterval;

    /**
     * 关闭按钮: 0不展示  1展示
     */
    private Integer showCloseButton;

    /**
     * 资源位挂件详情
     */
    private List<RoomPendantDetailDTO> pendantDetailList;
}


