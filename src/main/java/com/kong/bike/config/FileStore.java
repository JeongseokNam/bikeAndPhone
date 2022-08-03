package com.kong.bike.config;

import com.kong.bike.config.exeption.AttachFileException;
import com.kong.bike.entity.FileEntity;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStore {
//    @Value("C:/Users/Admin/Documents/newbike/src/main/resources/static/image/upload/summerNote/")
    @Value("C:/Users/rladm/OneDrive/문서/newbike/src/main/resources/static/image/upload/summerNote/")
    private String SAVE_SUMMER_PATH;
    //실제파일 저장위치
    //현서@Value("C:/Users/user/OneDrive/문서/newbike/src/main/resources/static/image/upload/")
    // 은비 @Value("C:/Users/rladm/OneDrive/문서/newbike/src/main/resources/static/image/upload/")
    //정석 @Value("C:/Users/Admin/Documents/newbike/src/pub/static/image/upload/")
    @Value("C:/Users/rladm/OneDrive/문서/newbike/src/main/resources/static/image/upload/")
    private String SAVE_PATH;
    //데이터 로드경로(데이터베이스저장)
    @Value("../image/upload/")
    private String LOCATION_SAVE_PATH;
    @Value("../image/upload/summerNote/")
    private String SUMMER_LOCATION_SAVE_PATH;
    public FileEntity uploadFile(MultipartFile file, int fileNum, long relatId,String fileDiv){//사진 업로드
    /**
     * 첨부파일 생성, 업로드 파일 데이터 반환
     * 메서드명 : uploadFile
     * 작성자 : Jeongseok
     * 작성일  : 2022-06-07
    **/
    SAVE_PATH = SAVE_PATH+fileDiv+"/";
    LOCATION_SAVE_PATH = LOCATION_SAVE_PATH+fileDiv+"/";
    /*path내 디렉터리 체크, 존재하지 않으면, 부모디렉터리 포함 모든 디렉터리 생성*/
    File dir = new File(SAVE_PATH);
        if (dir.exists() == false){
            dir.mkdirs();
        }
        FileEntity attach;
        try {
            /*파일 확장자*/
            final String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
            /*서버에 저장할 파일명 랜덤 문자열 + 확장자)*/
            final String saveFileNm = getRandomString()+"."+fileExt;
            /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
            File target = new File(SAVE_PATH, saveFileNm);
            file.transferTo(target);
            /*파일크기 구하기*/
            Path path = Paths.get(SAVE_PATH+saveFileNm);
            long fileSize = Files.size(path);
            /*파일 정보저장*/
            attach = new FileEntity();
            attach.setFileNum(fileNum);
            attach.setSaveFileNm(saveFileNm);
            attach.setRealFileNm(file.getOriginalFilename());
            attach.setFilePath(LOCATION_SAVE_PATH);
            attach.setFileExt(fileExt);
            attach.setFileSize(fileSize);
            attach.setFileDiv(fileDiv);
            attach.setRelatId(relatId);
            attach.setDelYn("N");

        } catch (IOException e) {

            throw new AttachFileException("[" + file.getOriginalFilename() +
                    "] failed to save file...",e.getCause());

        } catch (Exception e) {

            throw new AttachFileException("[" + file.getOriginalFilename() +
                    "] failed to save file...",e.getCause());

        }
        SAVE_PATH = "C:/Users/Admin/Documents/newbike/src/main/resources/static/image/upload/";
        LOCATION_SAVE_PATH = "../image/upload/";
        return attach;
    }

    private final String getRandomString() {
        /**
         * 서버에 저장할 랜덤 파일명 생성,반환
         * 메서드명 : getRandomString
         * 작성자 : Jeongseok
         * 작성일  : 2022-06-07
        **/
        return UUID.randomUUID().toString().replaceAll("-", "");
    }



    public FileEntity summernoteUp(MultipartFile file,long relatId) {

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(SAVE_SUMMER_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileEntity attach;
        try {
            /* 파일 확장자 */
            final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
            final String saveName = getRandomString() + "." + extension;

            /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
            File target = new File(SAVE_SUMMER_PATH, saveName);
            file.transferTo(target);
            /*파일크기 구하기*/
            Path path = Paths.get(SAVE_SUMMER_PATH+saveName);
            long bytes = Files.size(path);

            /* 파일 정보 저장 */
            attach = new FileEntity();

            attach.setSaveFileNm(saveName);
            attach.setRealFileNm(file.getOriginalFilename());
            attach.setFileSize(bytes);
            attach.setSaveFileNm(saveName);
            attach.setFileExt(extension);
            attach.setRelatId(relatId);
            attach.setDelYn("N");
            attach.setFilePath(SUMMER_LOCATION_SAVE_PATH);


            /* 파일 정보 추가 */
        } catch (IOException e) {
            e.printStackTrace();

            throw new AttachFileException("[" + file.getOriginalFilename() +
                    "] failed to save file...1");

        } catch (Exception e) {

            throw new AttachFileException("[" + file.getOriginalFilename() +
                    "] failed to save file...2");

        }
        return attach;
    }
}
