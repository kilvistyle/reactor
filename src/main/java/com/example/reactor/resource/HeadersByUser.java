package com.example.reactor.resource;

import com.example.reactor.entity.Header;
import com.example.reactor.entity.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class HeadersByUser implements Serializable {

    private User user;
    private List<Header> headers;

}
