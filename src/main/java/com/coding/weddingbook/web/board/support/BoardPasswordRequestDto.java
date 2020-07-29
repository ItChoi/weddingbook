package com.coding.weddingbook.web.board.support;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class BoardPasswordRequestDto {

    @Pattern(regexp = "^.*(?=^.{6,12}$)(?=.*\\d)(?=.*[a-zA-Z]).*$", message = "비밀번호는 영어와 숫자를 조합하여 6~12자리 이내로 입력해주세요.")
    private String password;

}
