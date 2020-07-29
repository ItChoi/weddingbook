package com.coding.weddingbook.web.board.domain;

import com.coding.weddingbook.common.config.BaseDateTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Board extends BaseDateTimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String content;

    private String writer;

    @JsonIgnore
    private String password;

    private String deleteStatus;

}
