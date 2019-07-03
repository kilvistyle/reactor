package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Body implements Serializable {

    private Long entryId;
    private String body;

}
