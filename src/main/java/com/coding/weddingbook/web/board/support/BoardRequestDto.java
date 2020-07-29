package com.coding.weddingbook.web.board.support;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardRequestDto {

    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 20, message = "제목은 20자를 넘을 수 없습니다.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Size(max = 200, message = "제목은 200자를 넘을 수 없습니다.")
    private String content;

    @NotBlank(message = "작성자 이름을 입력해주세요.")
    private String writer;

    @Pattern(regexp = "^.*(?=^.{6,12}$)(?=.*\\d)(?=.*[a-zA-Z]).*$", message = "비밀번호는 영어와 숫자를 조합하여 6~12자리 이내로 입력해주세요.")
    private String password;

}
