package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
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
            mainImageMapper.insert(trans.getTId(), transMainImage.getOriginalFilename());
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
//        Trans trans = mapper.selectByTId(id);
//
//        String mainImage = MainImageMapper.selectByTId(id);
//
//        trans.setMainImage(mainImage);
//        return trans;

        return mapper.selectByTId(id);
    }
    // 조회 테스트 중 (끝)--------------------------------------------------------------------------------------------------

    public void update(Trans trans) {
        mapper.update(trans);
    }

    public void delete(Integer id) {

        // 운송 상품 삭제전 type 테이블 을 삭제
        transTypeMapper.deleteByTId(id);

        // 운송 상품 삭제 하기
        mapper.deleteById(id);
    }

    public List<Trans> listPopularBus() {
        return mapper.selectPopularToBus();
    }

    public List<Trans> listPopularAir() {
        return mapper.selectPopularToAir();
    }
}
