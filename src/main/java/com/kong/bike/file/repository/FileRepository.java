package com.kong.bike.file.repository;

import com.kong.bike.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {

    FileEntity findByRelatIdAndFileNumAndDelYnAndFileDiv(Long relatId, int fileNum, String delYn, String fileDiv);

    List<FileEntity> findAllByRelatIdAndFileDivAndDelYn(Long relatId, String fileDiv, String delYn);
}