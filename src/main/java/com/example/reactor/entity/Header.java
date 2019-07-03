package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Header implements Serializable {

    private Long entryId;
    private String subject;
    private String userId;
    private String categoryId;
    private boolean isDraft;
    private Date createDate;
    private Date updateDate;
    private Long version;

}
