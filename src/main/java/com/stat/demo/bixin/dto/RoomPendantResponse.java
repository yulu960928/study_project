package com.stat.demo.bixin.dto;

import com.yupaopao.platform.common.annotation.Description;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 游戏房资源位挂件响应
 *
 * @author yulu
 */
@Data
public class RoomPendantResponse implements Serializable {
    private static final long serialVersionUID = 7865580471485715160L;

    @Description("挂件列表")
    private List<RoomPendantStyleDTO> roomPendantList;

}
