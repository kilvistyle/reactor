package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Header</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/23 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/23
 */
@Data
public class Header implements Serializable {

    private Long articleId;
    private String subject;
    private String userId;
    private String categoryId;
    private boolean isDraft;
    private Date createDate;
    private Date updateDate;
    private Long version;

}
