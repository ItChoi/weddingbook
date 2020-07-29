package com.coding.weddingbook.web.board;

import com.coding.weddingbook.common.support.CommonPageDto;
import com.coding.weddingbook.common.support.ResponseWrapperDto;
import com.coding.weddingbook.web.board.service.BoardService;
import com.coding.weddingbook.web.board.support.BoardPasswordRequestDto;
import com.coding.weddingbook.web.board.support.BoardRequestDto;
import com.coding.weddingbook.web.board.support.BoardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    // 게시글 추가
    @PostMapping("/board")
    public ResponseEntity<BoardResponseDto> writeBoard(@Valid @RequestBody BoardRequestDto requestDto, BindingResult errors) {
        if (errors.hasErrors()) {
            return (ResponseEntity<BoardResponseDto>) invalidResponseEntity(errors);
        }

        BoardResponseDto responseDto = boardService.save(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 게시글 리스트
    @GetMapping("/board")
    public ResponseEntity<ResponseWrapperDto> list(CommonPageDto commonPageDto) {
        ResponseWrapperDto dto = boardService.findAll(commonPageDto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // 게시글 상세
    @GetMapping("/board/{boardId}")
    public ResponseEntity<BoardResponseDto> detailBoard(@PathVariable("boardId") Long id) {
        BoardResponseDto findBoard = boardService.findById(id);

        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    // 비밀번호 일치 여부
    @PostMapping("/board/{boardId}")
    public ResponseEntity<Boolean> passwordCheck(@PathVariable("boardId") Long id,
                                                 @Valid @RequestBody BoardPasswordRequestDto requestDto, BindingResult errors) {
        if (errors.hasErrors()) {
            return (ResponseEntity<Boolean>) invalidResponseEntity(errors);
        }

        boolean samedPw = boardService.boardPasswordCheck(id, requestDto);
        if (samedPw) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
    }

    // 게시글 수정
    @PutMapping("/board")
    public ResponseEntity<BoardResponseDto> updateBoard(@Valid @RequestBody BoardRequestDto requestDto, BindingResult errors) {
        if (errors.hasErrors()) {
            return (ResponseEntity<BoardResponseDto>) invalidResponseEntity(errors);
        }

        BoardResponseDto responseDto = boardService.save(requestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    // 게시글 삭제
    @DeleteMapping("/board/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable("boardId") Long id) {
        boardService.deleteBoardById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private ResponseEntity<?> invalidResponseEntity(BindingResult errors) {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", errors.getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

}
