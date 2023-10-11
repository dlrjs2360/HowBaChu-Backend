package com.HowBaChu.howbachu.controller;

import com.HowBaChu.howbachu.domain.constants.ResponseCode;
import com.HowBaChu.howbachu.domain.dto.opin.OpinRequestDto;
import com.HowBaChu.howbachu.domain.dto.response.ResResult;
import com.HowBaChu.howbachu.service.OpinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/opin")
public class OpinController {

    private final OpinService opinService;

    @PostMapping
    public ResponseEntity<?> postOpin(@RequestBody OpinRequestDto requestDto,
                                              @ApiIgnore Authentication authentication) {

        ResponseCode responseCode = ResponseCode.OPIN_SAVE;
        return new ResponseEntity<>(ResResult.builder()
            .responseCode(responseCode)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .data(opinService.createOpin(requestDto, authentication.getName(), requestDto.getParentId()).getId())
            .build(), responseCode.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<?> listOpin() throws JsonProcessingException {
        ResponseCode responseCode = ResponseCode.OPIN_LIST;
        return new ResponseEntity<>(ResResult.builder()
            .responseCode(responseCode)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .data(opinService.getOpinList())
            .build(), responseCode.getHttpStatus());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> listOpin(@PathVariable("id") String id) {
        ResponseCode responseCode = ResponseCode.OPIN_LIST;
        return new ResponseEntity<>(ResResult.builder()
            .responseCode(responseCode)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .data(opinService.getOpinThread(Long.parseLong(id)))
            .build(), responseCode.getHttpStatus());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> updateOpin(@PathVariable("id") String id,
                                                @RequestBody OpinRequestDto requestDto,
                                                @ApiIgnore Authentication authentication) {

        ResponseCode responseCode = ResponseCode.OPIN_UPDATE;
        return new ResponseEntity<>(ResResult.builder()
            .responseCode(responseCode)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .data(opinService.updateOpin(requestDto, Long.parseLong(id), authentication.getName()).getId())
            .build(), responseCode.getHttpStatus());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteOpin(@PathVariable("id") String id,
                                                @ApiIgnore Authentication authentication) {

        ResponseCode responseCode = ResponseCode.OPIN_DELETE;
        return new ResponseEntity<>(ResResult.builder()
            .responseCode(responseCode)
            .code(responseCode.getCode())
            .message(responseCode.getMessage())
            .data(opinService.removeOpin(Long.parseLong(id), authentication.getName()))
            .build(), responseCode.getHttpStatus());
    }

}
