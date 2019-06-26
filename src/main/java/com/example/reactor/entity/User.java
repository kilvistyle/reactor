package com.example.reactor.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>User</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
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
