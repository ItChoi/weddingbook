package com.coding.weddingbook.web.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BoardController {

    // 게시글 추가
    @PostMapping("/board")
    public ResponseEntity<String> writeBoard() {


        return null;
    }

    // 게시글 리스트

    // 게시글 상세

    // 게시글 수정

    // 게시글 삭제

}
