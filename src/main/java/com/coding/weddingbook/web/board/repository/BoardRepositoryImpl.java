package com.coding.weddingbook.web.board.repository;

import com.coding.weddingbook.common.support.CommonPageDto;
import com.coding.weddingbook.web.board.domain.Board;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.coding.weddingbook.web.board.domain.QBoard.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Board> findAll(CommonPageDto commonPageDto) {
        Pageable pageable = getPageable(commonPageDto);

        QueryResults<Board> result = queryFactory
                .select(board)
                .from(board)
                .where(
                        board.deleteStatus.eq("N")
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.createdDate.asc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private Pageable getPageable(CommonPageDto commonPageDto) {
        return PageRequest.of(commonPageDto.getPage(), commonPageDto.getSize());
    }

}
