package com.kong.bike.phone;

import com.kong.bike.DTO.BoardListDTO;
import com.kong.bike.DTO.CommentListDTO;
import com.kong.bike.DTO.PhoneListDTO;
import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.comment.service.CommentService;
import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.entity.*;
import com.kong.bike.file.service.FileService;
import com.kong.bike.like.service.LikeService;
import com.kong.bike.member.service.MemberService;
import com.kong.bike.phone.service.PhoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PhoneController {

    @Autowired
    PhoneService phoneService;
    @Autowired
    MemberService memberService;
    @Autowired
    FileService fileService;
    @Autowired
    LikeService likeService;
    @Autowired
    CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/phone/phoneList") // 휴대폰 리스트 페이지
    public String phoneList(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails, Long phoneId
            , @PageableDefault(size = 3, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(value = "searchType", required = false) String searchType
            , @RequestParam(value = "location", required = false) String location
            , @RequestParam(value = "keyword", required = false) String keyword) {
        Page<PhoneBoardEntity> PhonePagingList;
        if (keyword != null||location != null) {
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setSearchType(searchType);
            searchDTO.setKeyword(keyword);
            searchDTO.setLocation(location);
            PhonePagingList = phoneService.searchPhoneList(searchDTO, pageable);
        } else {
            PhonePagingList = phoneService.findByDelYn(pageable);
        }
        List<PhoneListDTO> phoneList = PhonePagingList.stream().map(phone -> modelMapper.map(phone, PhoneListDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < phoneList.size(); i++) {
            FileEntity fileEntity;
            Long reletId = phoneList.get(i).getPhoneId();
            fileEntity = fileService.findByRelatIdAndFileNumAndDelYnAndFileDiv(reletId, 1, "N", "phone");
            System.out.println(fileEntity);
            if (fileEntity != null){
                phoneList.get(i).setFilePath(fileEntity.getFilePath());
                phoneList.get(i).setSaveFileNm(fileEntity.getSaveFileNm());
            }
            // ------------------------회원---------------------------------------
            PhoneBoardEntity phoneBoardEntity = phoneService.getPhoneInfo(phoneList.get(i).getPhoneId());
            phoneList.get(i).setMemberEntity(phoneBoardEntity.getMemberEntity());
        }

        model.addAttribute("phoneList", phoneList);

        /*페이지네이션*/
        model.addAttribute("phonePagingList", PhonePagingList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", PhonePagingList.hasNext());
        model.addAttribute("hasPrev", PhonePagingList.hasPrevious());
        return "phone/phoneList";
    }

    @GetMapping("/phone/phoneView") // 휴대폰 상세페이지
    public String phoneShowForm(Model model, Long phoneId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        PhoneBoardEntity phoneInfo = phoneService.getPhoneInfo(phoneId);
        MemberEntity memberInfo = phoneInfo.getMemberEntity();
        MemberEntity tempMember = null;
        if (principalDetails != null) {
            tempMember = principalDetails.getMemberEntity(); //로그인한 회원정보
            if (tempMember.getMemberId() != memberInfo.getMemberId()) { //로그인한 회원 != 글쓴 회원
                phoneService.viewCount(phoneId); //조회수 up
            }
        }
        List<FileEntity> fileLists = fileService.getFileList(phoneInfo.getPhoneId(),"phone");
        model.addAttribute("fileList", fileLists);
        // like entity
        if (tempMember != null) {
            LikeEntity likeEntity = likeService.findByMemberEntityAndRelatIdAndRelatDiv(tempMember, phoneInfo.getPhoneId(), "phone");
            model.addAttribute("like", likeEntity);
        }
        /*댓글, 작성자 list*/
        List<CommentListDTO> commentList = commentService.getCommentList(phoneId,"phone");

        model.addAttribute("commentList",commentList);
        model.addAttribute("fileList", fileLists);
        model.addAttribute("tempMember", tempMember);
        model.addAttribute("phoneInfo", phoneInfo);
        model.addAttribute("memberInfo", memberInfo);
        return "/phone/phoneView";
    }

    @GetMapping("/phone/phoneUpload") // 휴대폰 글등록 페이지
    public String phoneAddForm() {
        return "phone/phoneUpload";
    }

    @PostMapping("/phone/phoneUpload") // 휴대폰 글등록
    public String phoneAdd(@AuthenticationPrincipal PrincipalDetails principalDetails, PhoneBoardEntity phoneBoardEntity) {
        MemberEntity memberEntity = principalDetails.getMemberEntity();
        phoneService.save(phoneBoardEntity, memberEntity);
        return "redirect:/phone/phoneList";
    }

    @GetMapping("/phone/phoneModify") // 휴대폰 수정페이지
    public String phoneModify(Model model, Long phoneId) {
        PhoneBoardEntity phoneInfo = phoneService.getPhoneInfo(phoneId);
        MemberEntity memberInfo = phoneInfo.getMemberEntity();
        model.addAttribute("phoneInfo", phoneInfo);
        model.addAttribute("memberInfo", memberInfo);
        List<FileEntity> files = fileService.getFileList(phoneInfo.getPhoneId(),"phone");
        for (int i = 0; i < files.size(); i++) {
            for (int j = 0; j < 11; j++) {
                if (files.get(i).getFileNum() == j) {
                    model.addAttribute("file" + j, files.get(i));
                }
            }
        }
        return "/phone/phoneShow";
    }

    @PostMapping("/phone/phoneShow") // 휴대폰 수정버튼
    public String phoneUpdate(PhoneBoardEntity phoneBoardEntity) {
        System.out.println(phoneBoardEntity);
        phoneService.updatedPhone(phoneBoardEntity);
        return "redirect:/phone/phoneList";
    }

    @GetMapping("/phone/delete") // 휴대폰 게시물 삭제
    public String PhoneDelete(Long phoneId) {
        phoneService.PhoneDelete(phoneId);
        System.out.println(phoneId);
        return "redirect:/phone/phoneList";
    }

    @ResponseBody
    @PostMapping("/phone/soldOut") // 휴대폰 게시물 판매완료
    public void soldOut(Long phoneId) {
        phoneService.soldOut(phoneId);
    }

    @ResponseBody
    @PostMapping("/phone/soldOutCancel") // 휴대폰 게시물 판매완료 취소
    public void soldOutCancel(Long phoneId) {
        phoneService.soldOutCancel(phoneId);
    }



}




