package com.kong.bike.file;

import com.kong.bike.board.service.BoardService;
import com.kong.bike.config.FileStore;
import com.kong.bike.entity.FileEntity;
import com.kong.bike.file.service.FileService;
import com.kong.bike.notice.service.NoticeService;
import com.kong.bike.phone.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {

    @Autowired
    FileService fileService;
    @Autowired
    FileStore fileStore;

    @Autowired
    BoardService boardService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    PhoneService phoneService;

    // 바이크 게시물 사진파일 ------------------------------------------------------
    @PostMapping("/file/upload") // 바이크 게시물 사진파일
    @ResponseBody
    public void uploadFile(MultipartHttpServletRequest request, HttpServletRequest request2) throws IOException {
        /**
         * 파일저장,파일정보 저장
         * 메서드명 : uploadFile
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-07
         **/
        String fileDiv = request2.getParameter("fileDiv");
        int relatId = 0;
        if (fileDiv.equals("board")){
            relatId =boardService.getRecentBoardId();
        }
        if (fileDiv.equals("phone")){
            relatId =phoneService.getRecentPhoneId();
        }
        if (relatId<=0){
            relatId=1;
        }
        for (int i = 0; i < 11; i++) {
            if (request.getFile("file" + i) != null) {
                MultipartFile file = request.getFile("file" + i);
                FileEntity fileEntity = fileStore.uploadFile(file, i, relatId,fileDiv);
                fileService.save(fileEntity);
            }
        }
    }

    @PostMapping("/file/update") // 바이크 게시물 사진파일
    @ResponseBody
    public void updateFile(MultipartHttpServletRequest request, HttpServletRequest request2) throws IOException{
        /**
         * 파일저장,파일정보 업데이트
         * 메서드명 : updateFile
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-09
         **/
        int relatId = Integer.parseInt(request2.getParameter("relatId"));
        String fileDiv = request2.getParameter("fileDiv");
        System.out.println(relatId);
        for (int i = 0; i < 11; i++) {
            if (request.getFile("file"+i)!=null){
                MultipartFile file = request.getFile("file"+i);
                FileEntity fileEntity = fileStore.uploadFile(file,i,relatId,fileDiv);
                fileService.update(fileEntity,fileDiv);
            }

        }
    }
    @ResponseBody
    @PostMapping("/file/deleteFile") // 바이크 게시물 사진파일
    public void deleteFile(int fileNum,Long relatId,String fileDiv){
        fileService.deleteFile(fileNum,relatId,fileDiv);
    }

// ----------------------------------썸머노트-----------------------------------

    @ResponseBody
    @PostMapping("/upload/image") // 공지사항 썸머노트 이미지 등록
    public ResponseEntity<Map<String, Object>> uploadImage(MultipartHttpServletRequest request, HttpServletRequest request2) throws IOException {
        String relatDiv = request2.getParameter("relatDiv");
        int relatId = 0;
        if (relatDiv.equals("notice")){
            relatId = noticeService.getRecentNoticeId();
        }else {
            relatId = phoneService.getRecentPhoneId();
        }
        if (relatId <= 0) {
            relatId = 1;
        }
        MultipartFile file = request.getFile("file");
        FileEntity upload = fileStore.summernoteUp(file, relatId);
        Map<String, Object> map = new HashMap<>();
        map.put("img", upload.getFilePath()+upload.getSaveFileNm());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
