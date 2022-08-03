package com.kong.bike.board;

import com.kong.bike.DTO.BoardListDTO;
import com.kong.bike.DTO.CommentListDTO;
import com.kong.bike.DTO.SearchDTO;
import com.kong.bike.board.service.BoardService;
import com.kong.bike.comment.service.CommentService;
import com.kong.bike.config.auth.PrincipalDetails;
import com.kong.bike.entity.*;
import com.kong.bike.file.service.FileService;
import com.kong.bike.like.service.LikeService;
import com.kong.bike.member.service.MemberService;
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
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FileService fileService;

    @Autowired
    MemberService memberService;

    @Autowired
    CommentService commentService;

    @Autowired
    LikeService likeService;

    @GetMapping("/board/addPost") // 등록페이지
    public String addPostForm(Model model) {
        List<BikeBrandEntity> brandList = boardService.getBrandList();
        List<LocationEntity> locationList = boardService.getLocationList();
        model.addAttribute("brandList",brandList);
        model.addAttribute("locationList",locationList);
        return "/board/addPostForm";
    }

    @PostMapping("/board/addPost") // 글 등록
    public String create(BikeBoardEntity bikeBoardEntity, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        MemberEntity memberEntity = principalDetails.getMemberEntity();
        boardService.save(bikeBoardEntity, memberEntity);
        return "redirect:/board/list";
    }

    @GetMapping("/board/list") // 글 리스트
    public String boardList(Model model, @PageableDefault(size = 3, sort = "modifiedDate", direction = Sort.Direction.DESC) Pageable pageable
            , @RequestParam(value = "keyword", required = false) String keyword
            , @RequestParam(value = "bikeBrand", required = false) String bikeBrand
            , @RequestParam(value = "location", required = false) String location
            , @RequestParam(value = "bikeYear", required = false) String bikeYear
            , @RequestParam(value = "driveKm", required = false) Integer driveKm
            , @RequestParam(value = "bikeModel", required = false) String bikeModel) {
        /**
         * 게시글 리스트 출력 List 는 service에서 게시물,파일,작성자 데이터 DTO 변환해서 Model값 넘김
         * 검색기능 포함
         * 메서드명 : boardList
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-14
         **/
        Page<BikeBoardEntity> boardList;
        /*검색 기능 여부*/
        if (keyword != null ||bikeBrand!=null||location!=null||bikeYear!=null||driveKm!=null||bikeModel!=null) {
            SearchDTO searchDTO = new SearchDTO();
            searchDTO.setKeyword(keyword);
            searchDTO.setBikeBrand(bikeBrand);
            searchDTO.setLocation(location);
            searchDTO.setBikeYear(bikeYear);
            searchDTO.setBikeModel(bikeModel);
            /*주행거리 범위설정*/
            if (driveKm!=null){
                searchDTO.setDriveKm(driveKm);
                for (int i = 0; i < 20; i++) {
                    if (i==driveKm){
                        searchDTO.setKmMax(i*10000);
                        searchDTO.setKmMin(searchDTO.getKmMax()-10000);
                    }
                }
            }
            boardList = boardService.searchBoardList(searchDTO,pageable); //검색게시글리스트
        } else {
            boardList = boardService.boardList(pageable); //게시글리스트
        }

        //게시글 리스트에 해당하는 파일, 작성자정보 가져오기,DTO에 추가가
        List<BoardListDTO> totalList;
        totalList = boardList.stream().map(board -> modelMapper.map(board, BoardListDTO.class)).collect(Collectors.toList());
        for (int i = 0; i < totalList.size(); i++) {//게시글수 반복
            FileEntity tempFile = fileService.findByRelatIdAndFileNumAndDelYnAndFileDiv(totalList.get(i).getBoardId(), 1, "N","board");
            MemberEntity tempMember = memberService.findByMemberId(totalList.get(i).getMemberId());
            if (tempFile != null) {
                totalList.get(i).setFilePath(tempFile.getFilePath());
                totalList.get(i).setSaveFileNm(tempFile.getSaveFileNm());
            }
            totalList.get(i).setNickName(tempMember.getNickName());
        }
        model.addAttribute("totalList", totalList);
        /*제조사,지역 리스트*/
        List<BikeBrandEntity> brandList = boardService.getBrandList();
        List<LocationEntity> locationList = boardService.getLocationList();
        model.addAttribute("brandList",brandList);
        model.addAttribute("locationList",locationList);
        /*페이지네이션션*/
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", boardList.hasNext());
        model.addAttribute("hasPrev", boardList.hasPrevious());
        model.addAttribute("boardList", boardList);
        return "/board/list";
    }

    @GetMapping("/board/view")//글 상세보기
    public String boardView(Model model, Long boardId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        BikeBoardEntity boardInfo = boardService.getBoardInfo(boardId);
        MemberEntity memberInfo = boardInfo.getMemberEntity();
        MemberEntity tempMember = null;
        if (principalDetails!=null) { //로그인한 정보가 있을시
             tempMember = principalDetails.getMemberEntity(); //로그인한 회원정보
             if (tempMember.getMemberId() != memberInfo.getMemberId()){ //로그인한 회원 != 글쓴 회원
                 boardService.viewCount(boardId); //조회수 up
             }
        }
        List<FileEntity> fileLists = fileService.getFileList(boardInfo.getBoardId(),"board");

        /*댓글, 작성자 list*/
        List<CommentListDTO> commentList = commentService.getCommentList(boardInfo.getBoardId(),"board");
        /*좋아요 entity*/
        if (tempMember!=null){
            LikeEntity likeEntity = likeService.findByMemberEntityAndRelatIdAndRelatDiv(tempMember,boardInfo.getBoardId(),"board");
            model.addAttribute("like",likeEntity);
        }
        model.addAttribute("commentList",commentList);
        model.addAttribute("fileList",fileLists);
        model.addAttribute("tempMember",tempMember);
        model.addAttribute("boardInfo",boardInfo);
        model.addAttribute("memberInfo",memberInfo);
        return "/board/view";
    }

    @GetMapping("/board/modify")
    public String boardModifiedForm(Model model,Long boardId){
        BikeBoardEntity boardInfo = boardService.getBoardInfo(boardId);
        MemberEntity memberInfo = boardInfo.getMemberEntity();
        List<BikeBrandEntity> brandList = boardService.getBrandList();
        List<LocationEntity> locationList = boardService.getLocationList();
        model.addAttribute("boardInfo",boardInfo);
        model.addAttribute("memberInfo",memberInfo);
        model.addAttribute("brandList",brandList);
        model.addAttribute("locationList",locationList);
        //파일 리스트 조회 & 분리
        List<FileEntity> files = fileService.getFileList(boardInfo.getBoardId(),"board");
        for (int i = 0; i < files.size(); i++) {
            for (int j = 0; j < 11; j++) {
                if (files.get(i).getFileNum()==j){
                    model.addAttribute("file"+j,files.get(i));
                }
            }
        }

        //체크리스트 조회&분리
        String option1 = boardInfo.getTuningOptions1(); //편의옵션

        String[] option1Array = null;
        if (option1!=null){
            option1Array = option1.split(",");
        }
        String[] temp1 ={" 열선"," 파워put"," 가방류"," 오디오"," 경보기"," 안개등"};
        for (int i = 0; i < option1Array.length; i++) {
            for (int j = 0; j < temp1.length; j++) {
                if (option1Array[i].equals(temp1[j])){
                    model.addAttribute("option1"+j,option1Array[i]);
                }
            }
        }
        String option2 = boardInfo.getTuningOptions2(); //성능옵션

        String[] option2Array = null;
        if (option2!=null){
            option2Array = option2.split(",");
        }
        String[] temp2 ={" 배기 튜닝"," 흡기 튜닝"," 엔진 튜닝"," 섀시 튜닝"," 브레이크 튜닝"," 서스펜션 튜닝"};
        for (int i = 0; i < option2Array.length; i++) {
            for (int j = 0; j < temp2.length; j++) {
                if (option2Array[i].equals(temp2[j])){
                    model.addAttribute("option2"+j,option2Array[i]);
                }
            }
        }
        String option3 = boardInfo.getTuningOptions3(); //외관옵션
        String[] option3Array = null;
        if (option3!=null){
            option3Array = option3.split(",");
        }
        String[] temp3 ={" 크롬파츠"," 카본파츠"," 커스텀도색"," 보호가드"," LED"," 스크린/페어링"};
        for (int i = 0; i < option3Array.length; i++) {
            for (int j = 0; j < temp3.length; j++) {
                if (option3Array[i].equals(temp3[j])){
                    model.addAttribute("option3"+j,option3Array[i]);
                }
            }
        }
        String document = boardInfo.getDocuments();
        String[] documentArray = null;
        if (document!=null){
            documentArray = document.split(",");
        }
        String[] tempDocument ={" 사용신고 필증"," 사용폐지 증명서"," 제작증명서"," 서류없음"};
        for (int i = 0; i < documentArray.length; i++) {
            for (int j = 0; j < tempDocument.length; j++) {
                if (documentArray[i].equals(tempDocument[j])){
                    model.addAttribute("document"+j,documentArray[i]);
                }
            }
        }
        return "/board/modifiedForm";
    }

    @PostMapping ("/board/modify")
    public String boardModify(BikeBoardEntity bikeBoardEntity){
        boardService.modifiedBoard(bikeBoardEntity);
        return "redirect:/board/list";
    }

    @PostMapping("/board/soldOut")
    @ResponseBody
    public void soldOut(Long boardId){
        /**
         *판매완료 처리
         * 메서드명 : soldOut
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-20
        **/
        boardService.soldOut(boardId);
    }
    @PostMapping("/board/soldOutCancel")
    @ResponseBody
    public void soldOutCancel(Long boardId){
        /**
         *판매완료취소 처리
         * 메서드명 : soldOut
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-20
         **/
        boardService.soldOutCancel(boardId);
    }

    @GetMapping("/board/delete")
    public String deleteBoard (Long boardId){
        /**
         * 게시물 삭제처리
         * 메서드명 : deleteBoard
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-20
        **/
        boardService.deleteBoard(boardId);
        return "redirect:/board/list";
    }

    @PostMapping("/board/findModel")
    @ResponseBody
    public List<BikeModelEntity> findBikeModel(String bikeBrand){
        List<BikeModelEntity> bikeModelEntities = boardService.getModelListByBandName(bikeBrand);
       return bikeModelEntities;
    }
    @PostMapping("/board/findLocationDetail")
    @ResponseBody
    public List<LocationDetailEntity> findLocationDetail(String location){
        List<LocationDetailEntity> locationDetailEntities = boardService.getLocationDetailByLocationName(location);
        return locationDetailEntities;
    }

    @ResponseBody
    @PostMapping("/board/realNameCheck")
    public String realNameCheck(Long memberId, String inputName){
        MemberEntity memberEntity = memberService.findByMemberId(memberId);
        if (inputName.equals(memberEntity.getRealName())){
            return "true";
        } else {
            return null;
        }
    }
}
