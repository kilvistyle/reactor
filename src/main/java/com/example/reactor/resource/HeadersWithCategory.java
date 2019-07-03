package com.example.reactor.resource;

import com.example.reactor.entity.Category;
import com.example.reactor.entity.Header;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class HeadersWithCategory implements Serializable {

    private Category category;
    private List<Header> headers;

}
