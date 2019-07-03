package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {

    private Long entryId;
    private Long number;
    private String body;
    private String userId;
    private Date postedDate;

}
