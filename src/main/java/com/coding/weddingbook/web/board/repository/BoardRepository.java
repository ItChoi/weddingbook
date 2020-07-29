package com.coding.weddingbook.web.board.repository;

import com.coding.weddingbook.web.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    boolean existsByIdAndPassword(Long id, String encryptSha256);
}
