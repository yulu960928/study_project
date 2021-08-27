package com.stat.demo.god.message;

import lombok.Data;

import java.util.Date;

/**
 * @author liwei
 * @date 2021/8/26
 **/
@Data
public class MessageDto {

    private Long messageId;

    private String sourceCode;

    private Date bizTime;

    private String bizId;

    private String rowData;
}
