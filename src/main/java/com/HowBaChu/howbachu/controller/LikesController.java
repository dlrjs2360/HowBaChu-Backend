package com.HowBaChu.howbachu.controller;

import com.HowBaChu.howbachu.domain.constants.ResponseCode;
import com.HowBaChu.howbachu.domain.dto.likes.LikesRequestDto;
import com.HowBaChu.howbachu.domain.dto.opin.OpinRequestDto;
import com.HowBaChu.howbachu.domain.dto.response.ResResult;
import com.HowBaChu.howbachu.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping
    public ResponseEntity<ResResult> addLikes(@RequestBody LikesRequestDto requestDto,
                                              @ApiIgnore Authentication authentication) {

        ResponseCode responseCode = ResponseCode.LIKES_ADD;
        return new ResponseEntity<>(ResResult.builder()
            .responseCode(responseCode)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .data(likesService.addLikes(authentication.getName(), requestDto.getOpinId()))
            .build(), responseCode.getHttpStatus());
    }

    @DeleteMapping
    public ResponseEntity<ResResult> cancelLikes(@RequestBody LikesRequestDto requestDto,
                                              @ApiIgnore Authentication authentication) {

        ResponseCode responseCode = ResponseCode.LIKES_CANCEL;
        return new ResponseEntity<>(ResResult.builder()
            .responseCode(responseCode)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .data(likesService.cancelLikes(authentication.getName(), requestDto.getOpinId()))
            .build(), responseCode.getHttpStatus());
    }
}
