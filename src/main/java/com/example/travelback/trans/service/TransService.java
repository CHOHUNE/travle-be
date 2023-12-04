package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.dto.TransMainImage;
import com.example.travelback.trans.mapper.MainImageMapper;
import com.example.travelback.trans.mapper.TransMapper;
import com.example.travelback.trans.mapper.TransTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TransService {
    private final TransMapper mapper;
    private final TransTypeMapper transTypeMapper;
    private final MainImageMapper mainImageMapper;

    // 아마존 파일 업로드 ====================
    private final S3Client s3;

    @Value("${aws.s3.bucket.name}")
    private String bucket;

    @Value("${image.file.prefix}")
    private String urlPrefix;
    // 아마존 파일 업로드 ====================

    public void add(Trans trans, String type, MultipartFile transMainImage) throws IOException {
        // 상품 추가
        mapper.insert(trans);

        // transMainImage 테이블에 정보 저장
        // -> transport(tId), 이미지 파일 이름
        if(transMainImage != null) { // 파일을 넣었을때에만
            String url = urlPrefix + "travel/trans/mainImage/" + trans.getTId() + "/" + transMainImage.getOriginalFilename();
            mainImageMapper.insert(trans.getTId(), transMainImage.getOriginalFilename(), url);
            // 파일이 실제로 등록이 된 후에는 S3로 저장 기능 추가
            // 실제 파일을 S3 bucket에 upload (파일이 있으면 )
            upload(trans.getTId(), transMainImage);
        }

        // 상품 등록할 때에 상품 타입을 등록 하는 기능
        transTypeMapper.insert(trans.getTId(), type);
    }

    // 아마존 테스트 (시작) ----------------------------------------------------------------------------------------------
    private void upload(Integer tId, MultipartFile transMainImage) throws IOException {
        String key = "travel/trans/mainImage/" + tId + "/" + transMainImage.getOriginalFilename();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        // 파일저장 경로
        s3.putObject(objectRequest, RequestBody.fromInputStream(transMainImage.getInputStream(), transMainImage.getSize()));


    }
    // 아마존 테스트 (끝) ----------------------------------------------------------------------------------------------

    public List<Trans> list() {
        return mapper.selectAll();
    }

    // 조회 테스트 중 (시작) ------------------------------------------------------------------------------------------------
    public Trans get(Integer id) {
        Trans trans = mapper.selectByTId(id);

        TransMainImage mainImageName = mainImageMapper.selectNameByTId(id);

        if(mainImageName != null) {
            String url = urlPrefix + "travel/trans/mainImage/" + id + "/" + mainImageName.getName();
            mainImageName.setUrl(url);
        }

        trans.setMainImage(mainImageName);

        return trans;

    }
    // 조회 테스트 중 (끝)--------------------------------------------------------------------------------------------------

    public void update(Trans trans) {
        mapper.update(trans);
    }

    // 운송 상품 삭제 (시작)-------------------------------------------------------------------------------------------------
    public void delete(Integer id) {

        // 운송 상품 삭제전 type 테이블 을 삭제
        transTypeMapper.deleteByTId(id);

        // 운송 상품 삭제전 mainimage 테이블 삭제, aws DB 파일도 삭제
        deleteMainImage(id);

        // 운송 상품 삭제 하기
        mapper.deleteById(id);
    }

    // 운송 상품 삭제전 mainimage 테이블 삭제, aws DB 파일도 삭제
    private void deleteMainImage(Integer id) {
        TransMainImage transMainImage = mainImageMapper.selectNameByTId(id);

        if(transMainImage != null){
            String key = "travel/trans/mainImage/" + id + "/" + transMainImage.getName();
            DeleteObjectRequest objectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            // 파일삭제 경로로 삭제
            s3.deleteObject(objectRequest);
        }

        // 테이블 이미지 삭제
        mainImageMapper.deleteByTId(id);
    }
    // 운송 상품 삭제 (끝)--------------------------------------------------------------------------------------------------

    public List<Trans> listPopularBus() {
        return mapper.selectPopularToBus();
    }

    public List<Trans> listPopularAir() {
        return mapper.selectPopularToAir();
    }
}
