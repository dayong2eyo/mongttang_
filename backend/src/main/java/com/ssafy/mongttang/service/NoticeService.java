package com.ssafy.mongttang.service;


import com.ssafy.mongttang.dto.ReqNoticeCreateFormDto;
import com.ssafy.mongttang.dto.ResponseNoticeDetailDto;
import com.ssafy.mongttang.dto.ReqNoticeUpdateFormDto;
import com.ssafy.mongttang.dto.ResponseNoticeInfoDto;
import com.ssafy.mongttang.entity.Notice;
import com.ssafy.mongttang.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Notice createNotice(ReqNoticeCreateFormDto reqNoticeCreateFormDto) {
        return noticeRepository.save(reqNoticeCreateFormDto.toEntity());
    }

    public ResponseNoticeDetailDto getNotice(int noticeId) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if(notice.isPresent()) {
            return new ResponseNoticeDetailDto(notice.get());
        } else {
            return null;
        }
    }

    public Page<ResponseNoticeInfoDto> getNoticeList(int page, int limit) {
        Pageable paging = PageRequest.of(page, limit, Sort.Direction.DESC, "createdTime");
        Page<ResponseNoticeInfoDto> noticeList = noticeRepository.findAll(paging).map(notice-> new ResponseNoticeInfoDto(notice));
        return noticeList;
    }

    @Transactional
    public Notice updateNotice(int noticeId, ReqNoticeUpdateFormDto reqNoticeUpdateFormDto) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if(!notice.isPresent()) return null;

        notice.get().update(reqNoticeUpdateFormDto);
        return noticeRepository.save(notice.get());
    }

    @Transactional
    public int deleteNotice(int noticeId) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        if(notice.isPresent()) {
            noticeRepository.deleteById(noticeId);
            return 1;
        } else {
            return 0;
        }
    }

    public List<ResponseNoticeInfoDto> getNotices() {
        List<ResponseNoticeInfoDto> responseNoticeDetailDtoList = noticeRepository.findAll().stream().map(notice -> new ResponseNoticeInfoDto(notice)).collect(Collectors.toList());
        return responseNoticeDetailDtoList;
    }
}
