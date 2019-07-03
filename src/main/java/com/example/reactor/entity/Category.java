package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {

    private String categoryId;
    private String name;

}
