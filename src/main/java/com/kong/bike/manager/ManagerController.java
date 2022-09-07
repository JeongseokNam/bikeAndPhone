package com.kong.bike.manager;

import com.kong.bike.DTO.BoardListDTO;
import com.kong.bike.DTO.BusinessMemberListForManagerDTO;
import com.kong.bike.DTO.PhoneListDTO;
import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.board.service.BoardService;
import com.kong.bike.businessMember.service.BusinessMemberService;
import com.kong.bike.entity.*;
import com.kong.bike.file.service.FileService;
import com.kong.bike.member.service.MemberService;
import com.kong.bike.notice.service.NoticeService;
import com.kong.bike.phone.service.PhoneService;
import com.kong.bike.qna.service.QnaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ManagerController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private PhoneService phoneService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QnaService qnaService;
    @Autowired
    private BusinessMemberService businessMemberService;
    @Autowired
    private FileService fileService;

    @GetMapping("/manager/memberList") // 관리자페이지 - 사용자목록 관리
    public String memberList(Model model
            , @PageableDefault(size = 3, sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable
            , @RequestParam(value = "searchType", required = false) String searchType
            , @RequestParam(value = "keyword", required = false) String keyword){
        Page<MemberEntity> MemberPagingList;
        if (keyword!=null){
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setSearchType(searchType);
            searchDTO.setKeyword(keyword);
            MemberPagingList = memberService.searchMemberList(searchDTO,pageable);
        }else {
            MemberPagingList = memberService.findByDelYn(pageable);
        }
        List<MemberEntity> memberList = MemberPagingList.stream().map(member -> modelMapper.map(member, MemberEntity.class)).collect(Collectors.toList());
//        List<MemberEntity> memberList = memberService.findByDelYn("N");
        model.addAttribute("memberList", memberList);
        /*새 문의 개수*/
        QnaCountEntity qnaCountEntity = qnaService.getCountByCountName("newQna");
        if (qnaCountEntity!=null){
            int newQnaCount = qnaCountEntity.getCount();
            model.addAttribute("newQnaCount",newQnaCount);
        }
        /*페이지네이션*/
        model.addAttribute("qnaPagingList",MemberPagingList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", MemberPagingList.hasNext());
        model.addAttribute("hasPrev", MemberPagingList.hasPrevious());
        return "manager/memberList";
    }

    @GetMapping("/manager/postList") // 관리자페이지 - 게시물목록 관리
    public String postList(Model model
            ,@PageableDefault(size = 3, sort = "modifiedDate", direction = Sort.Direction.DESC)Pageable pageable
            , @RequestParam(value = "searchType", required = false) String searchType
            , @RequestParam(value = "keyword", required = false) String keyword){
        List<BoardListDTO> totalList;
        Page<BikeBoardEntity> boardList;
        if (keyword!=null){
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setSearchType(searchType);
            searchDTO.setKeyword(keyword);
            boardList=boardService.searchManagerBoardList(searchDTO,pageable);
        }else {
            boardList=boardService.boardList(pageable);
        }
        totalList = boardList.stream().map(board -> modelMapper.map(board, BoardListDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < totalList.size(); i++) {//게시글수 반복
            MemberEntity tempMember = memberService.findByMemberId(totalList.get(i).getMemberId());
            totalList.get(i).setNickName(tempMember.getNickName());
        }
        model.addAttribute("totalList", totalList);

        model.addAttribute("boardList",boardList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", boardList.hasNext());
        model.addAttribute("hasPrev", boardList.hasPrevious());
        return "manager/postList";
    }

    @ResponseBody
    @PostMapping("/manager/RoleChange") // 관리자페이지 - 사용자목록 관리 권한설정 변경
    public void roleChange(Long memberId, String memberRole) {
        memberService.roleUpdate(memberId, memberRole);
    }

    @ResponseBody
    @PostMapping("/manager/delete") // 관리자페이지 - 바이크게시물 목록 관리 삭제버튼
    public void deleteBoard (Long boardId){
        boardService.deleteBoard(boardId);
    }

    @ResponseBody
    @PostMapping("/manager/deletePhone") // 관리자페이지 - 휴대폰게시물 목록 관리 삭제버튼
    public void deletePhone (Long phoneId){
        phoneService.deletePhone(phoneId);
    }

    @GetMapping("/manager/delete")
    public String deleteNotice (Long noticeId){
        noticeService.deleteNotice(noticeId);
        return "redirect:/manager/noticeList";
    }

    @GetMapping("/manager/businessMemberList")
    public String businessMemberList(Model model
            ,@PageableDefault(size = 3, sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable
            ,@RequestParam(value = "searchType", required = false) String searchType
            ,@RequestParam(value = "keyword", required = false) String keyword
            ,@RequestParam(value = "soleOrCorporate", required = false) String soleOrCorporate){
        Page<MemberEntity> businessMemberList;
        if (keyword!=null||soleOrCorporate!=null){
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setSearchType(searchType);
            searchDTO.setKeyword(keyword);
            searchDTO.setSoleOrCorporate(soleOrCorporate);
            businessMemberList=businessMemberService.searchBusinessMemberList(searchDTO,pageable);
        }else {
            businessMemberList=businessMemberService.businessMemberList(pageable);
        }
        List<BusinessMemberListForManagerDTO> totalList = businessMemberList.stream()
                .map(member -> modelMapper.map(member, BusinessMemberListForManagerDTO.class))
                .collect(Collectors.toList());
        for (int i = 0; i <totalList.size(); i++) {
            Long relatId=totalList.get(i).getMemberId();
            String fileDiv = "businessLicense";
            System.out.println(relatId);
            FileEntity fileEntity = fileService.findByRelatIdAndFileNumAndDelYnAndFileDiv(relatId,1,"N",fileDiv);
            if (fileEntity!=null){
                totalList.get(i).setFilePath(fileEntity.getFilePath());
                totalList.get(i).setSaveFileNm(fileEntity.getSaveFileNm());
            }
        }
        model.addAttribute("totalList",totalList);
        model.addAttribute("businessMemberList",businessMemberList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", businessMemberList.hasNext());
        model.addAttribute("hasPrev", businessMemberList.hasPrevious());
        return "manager/businessMemberList";
    }
    
    @GetMapping("/manager/phoneList") // 관리자페이지 -> 휴대폰 게시물목록 관리
    public String phoneList(Model model
            , @PageableDefault(size = 3, sort = "modifiedDate", direction = Sort.Direction.DESC)Pageable pageable
            , @RequestParam(value = "searchType", required = false) String searchType
            , @RequestParam(value = "keyword", required = false) String keyword){
        List<PhoneListDTO> phoneList;
        Page<PhoneBoardEntity> phonePageList;
        if (keyword != null){
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setSearchType(searchType);
            searchDTO.setKeyword(keyword);
            phonePageList = phoneService.searchPhoneList(searchDTO, pageable);
        }else {
            phonePageList = phoneService.findByDelYn(pageable);
        }
        phoneList = phonePageList.stream().map(phone -> modelMapper.map(phone, PhoneListDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < phoneList.size(); i++) {
            PhoneBoardEntity phoneBoardEntity = phoneService.getPhoneInfo(phoneList.get(i).getPhoneId());
            MemberEntity tempMember = phoneBoardEntity.getMemberEntity();
            phoneList.get(i).getMemberEntity().setNickName(tempMember.getNickName());
        }
        model.addAttribute("phoneList", phoneList);
        /*페이지 네이션*/
        model.addAttribute("phonePageList",phonePageList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", phonePageList.hasNext());
        model.addAttribute("hasPrev", phonePageList.hasPrevious());
        return "manager/phoneList";
    }
    
}
