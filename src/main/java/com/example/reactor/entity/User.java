package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String userId;
    private String name;
    private String mail;
    private UserState userState;

    public enum UserState {
        VALID,
        EXPIRE,
        UNSUBSCRIBE
    }
}
