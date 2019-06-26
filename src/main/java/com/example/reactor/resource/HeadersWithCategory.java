package com.example.reactor.resource;

import com.example.reactor.entity.Category;
import com.example.reactor.entity.Header;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>HeadersWithCategory</p>
 * <p>TODO クラスコメント</p>
 * <p>
 * ・新規作成 2019/06/26 S.Chiba.<br>
 * </p>
 *
 * @author S.Chiba
 * @since 2019/06/26
 */
@Data
@Builder
public class HeadersWithCategory implements Serializable {

    private Category category;
    private List<Header> headers;

}
