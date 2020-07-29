package com.coding.weddingbook.web.board.repository;

import com.coding.weddingbook.common.support.CommonPageDto;
import com.coding.weddingbook.web.board.domain.Board;
import org.springframework.data.domain.Page;

public interface BoardRepositoryCustom {
    Page<Board> findAll(CommonPageDto commonPageDto);
}
