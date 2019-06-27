package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Comment</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/23 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/23
 */
@Data
public class Comment implements Serializable {

    private Long entryId;
    private Long number;
    private String body;
    private String userId;
    private Date postedDate;

}
