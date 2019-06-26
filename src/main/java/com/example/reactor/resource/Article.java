package com.example.reactor.resource;

import com.example.reactor.entity.Body;
import com.example.reactor.entity.Comment;
import com.example.reactor.entity.Header;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * <p>Article</p>
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
public class Article {

    public Header header;
    public Body body;
    public List<Comment> comments;

}
