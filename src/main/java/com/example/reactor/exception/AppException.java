package com.example.reactor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;

/**
 * <p>AppException</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
public class AppException extends ResponseStatusException {

    public AppException(@NotNull String message, @NotNull HttpStatus status) {
        super(status, message);
    }

    public AppException(@NotNull String message, @NotNull HttpStatus status, @NotNull Exception exception) {
        super(status, message, exception);
    }

}
