package com.kong.bike.file.service.impl;

import com.kong.bike.entity.FileEntity;
import com.kong.bike.file.repository.FileRepository;
import com.kong.bike.file.service.FileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileRepository fileRepository;

    @Override
    public void save(FileEntity fileEntity) {
        /**
         * 파일일정보저장
         * 메서드명 : save
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-08
         **/
        FileEntity fileInfo = new FileEntity();
        fileInfo.setFileNum(fileEntity.getFileNum());
        fileInfo.setRelatId(fileEntity.getRelatId());
        fileInfo.setFileDiv(fileEntity.getFileDiv());
        fileInfo.setSaveFileNm(fileEntity.getSaveFileNm());
        fileInfo.setRealFileNm(fileEntity.getRealFileNm());
        fileInfo.setFilePath(fileEntity.getFilePath());
        fileInfo.setFileExt(fileEntity.getFileExt());
        fileInfo.setFileSize(fileEntity.getFileSize());
        fileInfo.setFileDiv(fileEntity.getFileDiv());
        fileInfo.setDelYn(fileEntity.getDelYn());
        fileRepository.save(fileInfo);
    }

    @Override
    public FileEntity findByRelatIdAndFileNumAndDelYnAndFileDiv(Long relatId, int fileNum, String delYn,String fileDiv) {
        /**
         * 참조값,파일순서번호,삭제여부로 파일찾기
         * 메서드명 : findByRelatIdAndFileNumAndDelYn
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-15
         **/
        return fileRepository.findByRelatIdAndFileNumAndDelYnAndFileDiv(relatId, fileNum , delYn,fileDiv);
    }

    @Override
    public List<FileEntity> getFileList(Long boardId,String fileDiv) {
        /**
         * 파일리스트 가져오기
         * 메서드명 : getFileList
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-16
         **/
        return fileRepository.findAllByRelatIdAndFileDivAndDelYn(boardId,fileDiv, "N");
    }

    @Override
    public void update(FileEntity fileEntity,String fileDiv) {
        /**
         * 파일정보 업데이트
         * 메서드명 : update
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-09
         **/
        //수정대상 데이터조회
        Long relatId = fileEntity.getRelatId();
        int fileNum = fileEntity.getFileNum();
        FileEntity origin = fileRepository.findByRelatIdAndFileNumAndDelYnAndFileDiv(relatId, fileNum, fileDiv, "N");
        if (origin != null) {
            //수정내용 밀어넣기
            fileEntity.setFileId(origin.getFileId());
            BeanUtils.copyProperties(fileEntity, origin);
            fileRepository.save(origin);
        } else {
            System.out.println("없어");
            fileRepository.save(fileEntity);
        }
    }

    @Override
    public void deleteFile(int fileNum, Long relatId, String fileDiv) {
        /**
         * 파일삭제 delYn 변경처리
         * 메서드명 : deletFile
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-17
         **/
        FileEntity target = fileRepository.findByRelatIdAndFileNumAndDelYnAndFileDiv(relatId, fileNum,fileDiv, "N");
        if (target != null) {
            target.setDelYn("Y");
            fileRepository.save(target);
        }
    }
}
