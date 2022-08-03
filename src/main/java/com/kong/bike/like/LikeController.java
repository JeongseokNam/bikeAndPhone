package com.kong.bike.like;

import com.kong.bike.board.service.BoardService;
import com.kong.bike.like.service.LikeService;
import com.kong.bike.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LikeController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private LikeService likeService;

    @ResponseBody
    @PostMapping("/like/addLike") // 바이크 글 상세보기 - 좋아요 버튼
    public void addLike(Long boardId, Long memberId,String relatDiv) {
        likeService.addLike(boardId, memberId,relatDiv);
    }

    @ResponseBody
    @PostMapping("/phone/clickLike") // 글 상세보기 - 좋아요 버튼
    public void clickLike(Long phoneId, Long memberId, String relatDiv) {
        likeService.addLike(phoneId, memberId, relatDiv);
    }

    @ResponseBody
    @PostMapping("/like/delLike") // 바이크, 휴대폰 글 상세보기 - 좋아요 취소버튼
    public void delLike(Long likeId) {
        likeService.delLike(likeId);
    }


}
