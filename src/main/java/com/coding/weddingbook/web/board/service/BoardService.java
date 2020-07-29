package com.coding.weddingbook.web.board.service;

import com.coding.weddingbook.common.exception.UserNotFoundException;
import com.coding.weddingbook.common.support.CommonPageDto;
import com.coding.weddingbook.common.support.Pagination;
import com.coding.weddingbook.common.support.ResponseWrapperDto;
import com.coding.weddingbook.common.utils.SecurityUtils;
import com.coding.weddingbook.web.board.domain.Board;
import com.coding.weddingbook.web.board.repository.BoardRepository;
import com.coding.weddingbook.web.board.support.BoardPasswordRequestDto;
import com.coding.weddingbook.web.board.support.BoardRequestDto;
import com.coding.weddingbook.web.board.support.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public BoardResponseDto save(BoardRequestDto requestDto) {
        Board board = modelMapper.map(requestDto, Board.class);
        board.setDeleteStatus("N");

        if (requestDto.getId() == null) {
            board.setPassword(SecurityUtils.encryptSha256(requestDto.getPassword()));
            createBoard(board);
        } else {
            updateBoard(board);
        }

        return modelMapper.map(board, BoardResponseDto.class);
    }

    public ResponseWrapperDto findAll(CommonPageDto commonPageDto) {
        Page<Board> findBoardList = boardRepository.findAll(commonPageDto);

        return ResponseWrapperDto.builder()
                .code("200")
                .message("success")
                .list(findBoardList.getContent())
                .pagination(
                        Pagination.initPagination(findBoardList)
                )
                .build();
    }

    public BoardResponseDto findById(Long id) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));

        return modelMapper.map(findBoard, BoardResponseDto.class);
    }

    private void createBoard(Board board) {
        boardRepository.save(board);
    }

    private void updateBoard(Board inputBoard) {
        Board findBoard = boardRepository.findById(inputBoard.getId()).orElseThrow(() -> new UserNotFoundException(inputBoard.getId().toString()));

        findBoard.setTitle(inputBoard.getTitle());
        findBoard.setContent(inputBoard.getContent());
    }

    public boolean boardPasswordCheck(Long id, BoardPasswordRequestDto requestDto) {
        return boardRepository.existsByIdAndPassword(id, SecurityUtils.encryptSha256(requestDto.getPassword()));
    }

    public void deleteBoardById(Long id) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        findBoard.setDeleteStatus("Y");
    }
}
