package com.kong.bike.notice;

import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.NoticeEntity;
import com.kong.bike.member.service.MemberService;
import com.kong.bike.notice.service.NoticeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NoticeController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/manager/notice") // 관리자페이지 -> 공지사항 -> 등록하러가기
    public String notice(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        MemberEntity memberEntity = principalDetails.getMemberEntity();
        model.addAttribute("memberId", memberEntity);
        return "/manager/notice";
    }

    @PostMapping("/manager/addNotice") // 공지사항 등록버튼
    public String addNotice(@AuthenticationPrincipal PrincipalDetails principalDetails, NoticeEntity noticeEntity){
        MemberEntity memberEntity = principalDetails.getMemberEntity();
        noticeService.save(noticeEntity, memberEntity);
        return "redirect:/manager/noticeList";
    }

    @GetMapping("/manager/noticeList") // 관리자페이지 공지사항 리스트 관리
    public String noticeList(Model model
            , @PageableDefault(size = 3, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable){
        Page<NoticeEntity> noticePagingList = noticeService.findByDelYn(pageable);
        List<NoticeEntity> noticeList = noticePagingList.stream().map(notice -> modelMapper.map(notice, NoticeEntity.class)).collect(Collectors.toList());
//        List<NoticeEntity> noticeList = noticeService.findByDelYn("N");
        model.addAttribute("noticeList", noticeList);

        /*페이지네이션*/
        model.addAttribute("noticePagingList",noticePagingList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", noticePagingList.hasNext());
        model.addAttribute("hasPrev", noticePagingList.hasPrevious());
        return "/manager/noticeList";
    }

    @GetMapping("/manager/noticeShow") // 관리자페이지 공지사항 수정페이지
    public String noticeShow (Long noticeId, Model model){
        NoticeEntity target = noticeService.findByNoticeId(noticeId);
        model.addAttribute("target", target);
        return "/manager/noticeShow";
    }

    @PostMapping("/manager/modifyNotice") // 관리자페이지 공지사항 수정버튼
    public String showNotice(NoticeEntity noticeEntity){
        noticeService.modifyNotice(noticeEntity);
        return "redirect:/manager/noticeList";
    }
}
