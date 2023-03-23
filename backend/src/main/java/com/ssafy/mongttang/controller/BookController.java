package com.ssafy.mongttang.controller;

import com.ssafy.mongttang.dto.*;
import com.ssafy.mongttang.entity.*;
import com.ssafy.mongttang.service.BookService;
import com.ssafy.mongttang.util.TokenUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private static final String MESSAGE = "message";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final BookService bookService;

    @ApiOperation(value = "동화 저장(작성 완료 + 임시저장)", notes = "그림 작가가 처음 작성한 그림을 저장한다.", response = Map.class)
    @PostMapping("/draw/{userId}")
    public ResponseEntity<Map<String,Object>> createBook(@ApiParam(value = "등록된 사진 리스트", required = true, example = "0")
                                                           @RequestPart(value = "imgList", required = false) ArrayList<MultipartFile> imgList,
                                                           @Valid @ApiParam(value = "챌린지 아이디, 동화 제목, 줄거리, 내용, 작가, 완료여부", required = true, example = "0")
                                                           @RequestPart(value = "BookContent", required = false) ReqCreateBookDto reqCreateBookDto,
                                                           @ApiParam(value = "작가 아이디", required = true, example = "0")
                                                           @PathVariable int userId, Principal principal){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(userId, principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        try {
            int bookId = bookService.createBook(userId,reqCreateBookDto,imgList);
            if(bookId > 0){
                resultMap.put(MESSAGE,SUCCESS);
                resultMap.put("bookId",bookId);
                status = HttpStatus.OK;
            }else{
                resultMap.put(MESSAGE, FAIL);
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (IOException e) {
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "동화 수정(작성 완료 + 임시저장)", notes = "그림 작가가 임시저장한 그림을 저장한다.", response = Map.class)
    @PatchMapping("/draw/{userId}")
    public ResponseEntity<Map<String,Object>> updateBook(@ApiParam(value = "등록된 사진 리스트", required = true, example = "0")
                                                         @RequestPart(value = "imgList", required = false) ArrayList<MultipartFile> imgList,
                                                         @Valid @ApiParam(value = "챌린지 아이디, 동화 아이디, 동화 제목, 줄거리, 내용, 작가", required = true, example = "0")
                                                         @RequestPart(value = "BookContent", required = false) ReqUpdateBookDto reqUpdateBookDto,
                                                         @ApiParam(value = "작가 아이디", required = true, example = "0")
                                                         @PathVariable int userId, Principal principal){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(userId, principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        if(imgList != null){
            try {
                int bookId = bookService.updateBook(userId,reqUpdateBookDto,imgList);
                if(bookId > 0){
                    resultMap.put(MESSAGE,SUCCESS);
                    resultMap.put("bookId",bookId);
                    status = HttpStatus.OK;
                }else{
                    resultMap.put(MESSAGE, FAIL);
                    status = HttpStatus.BAD_REQUEST;
                }
            } catch (IOException e) {
                resultMap.put(MESSAGE, FAIL);
                status = HttpStatus.BAD_REQUEST;
            }
        }else{
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "작가 동화 삭제", notes = "임시저장 한 동화를 삭제한다.", response = Map.class)
    @DeleteMapping("/draw/{userId}")
    public ResponseEntity<Map<String,Object>> deleteBook(@ApiParam(value = "작가 아이디", required = true, example = "0") @PathVariable int userId,
                                                                 @ApiParam(value = "동화 아이디", required = true, example = "0") @RequestParam int bookId, Principal principal){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(userId, principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        int isDeleted= bookService.deleteBook(userId,bookId);

        if(isDeleted > 0){
            resultMap.put(MESSAGE,SUCCESS);
            status = HttpStatus.OK;
        }else{
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "동화 좋아요 등록", notes = "동화 좋아요를 등록한다.", response = Map.class)
    @PostMapping("/booklike")
    public ResponseEntity<Map<String,Object>> createBookLike(@ApiParam(value = "회원 아이디", required = true, example = "0") @RequestParam int userId,
                                                           @ApiParam(value = "동화 아이디", required = true, example = "0") @RequestParam int bookId, Principal principal){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(userId, principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        BookLike bookLike = bookService.createBookLike(userId,bookId);

        if(bookLike != null){
            resultMap.put(MESSAGE,SUCCESS);
            resultMap.put("isLiked",true);
            status = HttpStatus.OK;
        }else{
            resultMap.put(MESSAGE, FAIL);
            resultMap.put("isLiked",false);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다.", response = Map.class)
    @PostMapping("/comment")
    public ResponseEntity<Map<String, Object>> createComment(@ApiParam(value = "회원 아이디, 동화 아이디, 댓글 내용 dto") @RequestBody ReqCreateCommentDto reqCreateCommentDto, Principal principal) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(reqCreateCommentDto.getCommentUserId(), principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        ArrayList<ResponseCommentDto> comments = bookService.createComment(reqCreateCommentDto);

        if(comments == null){
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.BAD_REQUEST;
        }else{
            resultMap.put(MESSAGE,SUCCESS);
            resultMap.put("comments",comments);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.", response = Map.class)
    @PatchMapping("/comment")
    public ResponseEntity<Map<String, Object>> updateComment(@ApiParam(value = "댓글 아이디, 회원 아이디, 동화 아이디, 댓글 내용 dto") @RequestBody ReqUpdateCommentDto reqUpdateCommentDto, Principal principal) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(reqUpdateCommentDto.getCommentUserId(), principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        ArrayList<ResponseCommentDto> comments = bookService.updateComment(reqUpdateCommentDto);

        if(comments == null){
            resultMap.put(MESSAGE, FAIL);
            status = HttpStatus.BAD_REQUEST;
        }else{
            resultMap.put(MESSAGE,SUCCESS);
            resultMap.put("comments",comments);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @ApiOperation(value = "동화 좋아요 취소", notes = "동화 좋아요를 취소한다.", response = Map.class)
    @DeleteMapping("/booklike")
    public ResponseEntity<Map<String,Object>> cancleBookLike(@ApiParam(value = "회원 아이디", required = true, example = "0") @RequestParam int userId,
                                                           @ApiParam(value = "동화 아이디", required = true, example = "0") @RequestParam int bookId, Principal principal){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(userId, principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        int isDeleted = bookService.cancleBookLike(userId,bookId);

        if(isDeleted > 0){
            resultMap.put(MESSAGE,SUCCESS);
            resultMap.put("isLiked",false);
            status = HttpStatus.OK;
        }else{
            resultMap.put(MESSAGE, FAIL);
            resultMap.put("isLiked",true);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "댓글 좋아요 등록", notes = "댓글 좋아요를 등록한다.", response = Map.class)
    @PostMapping("/commentlike")
    public ResponseEntity<Map<String,Object>> createCommentLike(@ApiParam(value = "회원 아이디", required = true, example = "0") @RequestParam int userId,
                                                             @ApiParam(value = "댓글 아이디", required = true, example = "0") @RequestParam int commentId, Principal principal){
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        if(TokenUtils.compareUserIdAndToken(userId, principal,resultMap)) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }

        CommentLike commentLike = bookService.createCommentLike(userId,commentId);

        if(commentLike != null){
            resultMap.put(MESSAGE,SUCCESS);
            resultMap.put("isLiked",true);
            status = HttpStatus.OK;
        }else{
            resultMap.put(MESSAGE, FAIL);
            resultMap.put("isLiked",false);
            status = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

}
