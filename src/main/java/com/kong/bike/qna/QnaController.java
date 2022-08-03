package com.kong.bike.qna;

import com.kong.bike.DTO.BoardListDTO;
import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.entity.MemberEntity;
import com.kong.bike.entity.QnaEntity;
import com.kong.bike.qna.service.QnaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class QnaController {


    @Autowired
    QnaService qnaService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/qna/main")
    public String qnaMain(Model model
            , @AuthenticationPrincipal PrincipalDetails principalDetails
            , @PageableDefault(size = 3, sort = "questionTime", direction = Sort.Direction.DESC)Pageable pageable){
        MemberEntity memberEntity = principalDetails.getMemberEntity();
        Page<QnaEntity> qnaPagingList = qnaService.findByQuestionMemberEntity(memberEntity,pageable);
        List<QnaEntity> qnaList = qnaPagingList.stream().map(qna -> modelMapper.map(qna, QnaEntity.class)).collect(Collectors.toList());
        model.addAttribute("qnaList",qnaList);
        /*페이지네이션*/
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", qnaPagingList.hasNext());
        model.addAttribute("hasPrev", qnaPagingList.hasPrevious());
        return "/qna/main";
    }

    @PostMapping("/qna/addQuestion")
    public String addQuestion(String qnaTitle, String questionContent
            , @AuthenticationPrincipal PrincipalDetails principalDetails){
        MemberEntity memberEntity = principalDetails.getMemberEntity();
        qnaService.addQuestion(qnaTitle,questionContent,memberEntity);
        return "redirect:/qna/main";
    }

    @ResponseBody
    @PostMapping("/qna/delete")
    public void deleteQna(Long qnaId){
        qnaService.deleteQna(qnaId);
    }

    @GetMapping("/manager/qnaList")
    public String qnaList (Model model
            , @PageableDefault(size = 3, sort = "questionTime", direction = Sort.Direction.DESC)Pageable pageable
            , @RequestParam(value = "searchType", required = false) String searchType
            , @RequestParam(value = "keyword", required = false) String keyword){
        Page<QnaEntity> qnaPagingList;
        if (keyword!=null){
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setSearchType(searchType);
            searchDTO.setKeyword(keyword);
            qnaPagingList=qnaService.searchQnaList(searchDTO,pageable);
        }else {
            qnaPagingList=qnaService.findByDelYn(pageable);
        }
        List<QnaEntity> qnaList = qnaPagingList.stream().map(qna -> modelMapper.map(qna, QnaEntity.class)).collect(Collectors.toList());
        model.addAttribute("qnaList",qnaList);
        /*새글갯수 초기화*/
        qnaService.resetCountByCountName("newQna");
        /*페이지네이션*/
        model.addAttribute("qnaPagingList",qnaPagingList);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", qnaPagingList.hasNext());
        model.addAttribute("hasPrev", qnaPagingList.hasPrevious());
        return "/manager/qnaList";
    }
    @PostMapping("/qna/addAnswer")
    public String addAnswer(Long qnaId, String answerContent
            , @AuthenticationPrincipal PrincipalDetails principalDetails){
        MemberEntity answerMember = principalDetails.getMemberEntity();
        qnaService.addAnswer(qnaId,answerContent,answerMember);
       return "redirect:/manager/qnaList";
    }
}
