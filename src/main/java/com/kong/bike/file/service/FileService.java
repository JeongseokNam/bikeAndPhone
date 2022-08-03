package com.kong.bike.file.service;

import com.kong.bike.entity.FileEntity;

import java.util.List;

public interface FileService {
    void save(FileEntity fileEntity);

    FileEntity findByRelatIdAndFileNumAndDelYnAndFileDiv(Long relatId, int fileNum, String delYn,String fileDiv);

    List<FileEntity> getFileList(Long boardId,String fileDiv);

    void update(FileEntity fileEntity, String fileDiv);

    void deleteFile(int fileNum, Long boardId,String fileDiv);

}
