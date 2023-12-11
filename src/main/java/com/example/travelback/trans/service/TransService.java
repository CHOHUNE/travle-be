package com.example.travelback.trans.service;

import com.example.travelback.trans.dto.Trans;
import com.example.travelback.trans.dto.TransContentImages;
import com.example.travelback.trans.dto.TransMainImage;
import com.example.travelback.trans.mapper.*;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TransService {
    private final TransMapper mapper;
    private final TransTypeMapper transTypeMapper;
    private final MainImageMapper mainImageMapper;
    private final ContentImagesMapper contentImagesMapper;
    private final TransLikeMapper transLikeMapper;

    // 아마존 파일 업로드 ====================
    private final S3Client s3;

    @Value("${aws.s3.bucket.name}")
    private String bucket;

    @Value("${image.file.prefix}")
    private String urlPrefix;
    // 아마존 파일 업로드 ====================

    // 운송 상품 등록 (시작) ------------------------------------------------------------------------------------------------
    public void add(Trans trans, String type, MultipartFile transMainImage, MultipartFile[] transContentImages) throws IOException {
        // 상품 추가
        mapper.insert(trans);

        // transMainImage 테이블에 정보 저장
        // -> transport(tId), 이미지 파일 이름
        // 메인 이미지
        if(transMainImage != null) { // 파일을 넣었을때에만
            String url = urlPrefix + "travel/trans/mainImage/" + trans.getTId() + "/" + transMainImage.getOriginalFilename();
            mainImageMapper.insert(trans.getTId(), transMainImage.getOriginalFilename(), url);
            // 파일이 실제로 등록이 된 후에는 S3로 저장 기능 추가
            // 실제 파일을 S3 bucket에 upload (파일이 있으면 )
            uploadMainImage(trans.getTId(), transMainImage);
        }

        // 배열로 들어오는 상품 상세 이미지
        if(transContentImages != null) {
            for (int i = 0; i < transContentImages.length; i++) {
                String url = urlPrefix + "travel/trans/contentImages/" + trans.getTId() + "/" + transContentImages[i].getOriginalFilename();
                contentImagesMapper.insert(trans.getTId(), transContentImages[i].getOriginalFilename(), url);
                // 파일 정보를 s3 bucket 에 저장 시키기
                uploadContentImages(trans.getTId(), transContentImages[i]);
            }
        }

        // 상품 등록할 때에 상품 타입을 등록 하는 기능
        transTypeMapper.insert(trans.getTId(), type);
    }

    // 운송 상품 content 이미지 업로드 (시작) -------------------------------------------------------------------------------------
    private void uploadContentImages(Integer tId, MultipartFile transContentImage) throws IOException {
        String key = "travel/trans/contentImages/" + tId + "/" + transContentImage.getOriginalFilename();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        // 파일저장 경로
        s3.putObject(objectRequest, RequestBody.fromInputStream(transContentImage.getInputStream(), transContentImage.getSize()));
    }
    // 운송 상품 content 이미지 업로드 (끝) --------------------------------------------------------------------------------------

    // 운송 상품 메인 이미지 업로드 (시작) -------------------------------------------------------------------------------------
    private void uploadMainImage(Integer tId, MultipartFile transMainImage) throws IOException {
        String key = "travel/trans/mainImage/" + tId + "/" + transMainImage.getOriginalFilename();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        // 파일저장 경로
        s3.putObject(objectRequest, RequestBody.fromInputStream(transMainImage.getInputStream(), transMainImage.getSize()));


    }
    // 운송 상품 메인 이미지 업로드 (끝) --------------------------------------------------------------------------------------
    // 운송 상품 등록 (끝) ------------------------------------------------------------------------------------------------

    public Map<String, Object> list(String type, Integer page) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pageInfo = new HashMap<>();

        int countAll = 0;
        if( type.equals("bus") ) {
            countAll = mapper.countByTypeName("bus");
        } else {
            countAll = mapper.countByTypeName("air");
        }

        int lastPageNumber = (countAll - 1) / 10 + 1;
        int startPageNumber = (page - 1) / 10 * 10 + 1;
        int endPageNumber = startPageNumber + 9;
        endPageNumber = Math.min(endPageNumber,lastPageNumber);

        int prePageNumber = startPageNumber - 10;
        int nextPageNumber = endPageNumber + 1;

        pageInfo.put("startPageNumber", startPageNumber);
        pageInfo.put("endPageNumber", endPageNumber);
        if(prePageNumber > 0) {
            pageInfo.put("prevPageNumber",0);
        }
        if(nextPageNumber <= lastPageNumber) {
            pageInfo.put("nextPageNumber",0);
        }

        int from = (page - 1) * 10;

        map.put("transList", mapper.selectAllByTypeName(type,from));
        map.put("pageInfo", pageInfo);

        return map;
    }

    // 운송 상품 해당 아이디 조회 (시작) ------------------------------------------------------------------------------------------------
    public Trans get(Integer id) {
        Trans trans = mapper.selectByTId(id);

        TransMainImage mainImageName = mainImageMapper.selectNameByTId(id);

        if(mainImageName != null) {
            String url = urlPrefix + "travel/trans/mainImage/" + id + "/" + mainImageName.getName();
            mainImageName.setUrl(url);
        }

        List<TransContentImages> contentImages = contentImagesMapper.selectNameByTId(id);
        if(contentImages != null) {
            for (TransContentImages contentImage: contentImages) {
                String url = urlPrefix + "travel/trans/contentImages/" + id + "/" + contentImage.getName();
                contentImage.setUrl(url);
            }
            trans.setContentImages(contentImages);
        }



        trans.setMainImage(mainImageName);

        return trans;

    }
    // 운송 상품 해당 아이디 조회 (끝)--------------------------------------------------------------------------------------------------

    // 운송 상품 업데이트 (시작)--------------------------------------------------------------------------------------------------
    public void update(Trans trans,
                       Integer removeMainImageId,
                       MultipartFile transMainImage,
                       List<Integer> removeContentImageIds,
                       MultipartFile[] transContentImages) throws IOException {

        // 메인 이미지 업데이트 전 메인 이미지 지우기가 있다면 지우기
        if(removeMainImageId != null) {
            // s3에서 지우기
            TransMainImage mainImage = mainImageMapper.selectById(removeMainImageId);
            String key = "travel/trans/mainImage/" + trans.getTId() + "/" + mainImage.getName();
            DeleteObjectRequest objectRequest = DeleteObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build();
            s3.deleteObject(objectRequest);
            // db에서 지우기
            mainImageMapper.deleteById(removeMainImageId);
        }

        // 메인 이미지를 지우고 난 후에는 업로드 이미지 넣기
        if(transMainImage != null) {
            // s3에 업로드
            uploadMainImage(trans.getTId(), transMainImage);
            // db에 업로드
            String url = urlPrefix + "travel/trans/mainImage/" + trans.getTId() + "/" + transMainImage.getOriginalFilename();
            mainImageMapper.insert(trans.getTId(), transMainImage.getOriginalFilename(), url);
        }

        // 컨텐츠 이미지 중 선택한 것이 있다면 지우기
        if(removeContentImageIds != null){
            // s3에서 지우기
            for (Integer id : removeContentImageIds){
                TransContentImages contentImages = contentImagesMapper.selectById(id);
                String key = "travel/trans/contentImages/" + trans.getTId() + "/" + contentImages.getName();
                DeleteObjectRequest objectRequest = DeleteObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build();
                // 파일삭제 경로로 삭제
                s3.deleteObject(objectRequest);

                // db에서 지우기
                contentImagesMapper.deleteById(id);
            }
        }

        // 추가한 파일 이 있다면 추가하기
        if(transContentImages != null) {
            for (int i = 0; i < transContentImages.length; i++) {
                String url = urlPrefix + "travel/trans/contentImages/" + trans.getTId() + "/" + transContentImages[i].getOriginalFilename();
                contentImagesMapper.insert(trans.getTId(), transContentImages[i].getOriginalFilename(), url);
                // 파일 정보를 s3 bucket 에 저장 시키기
                uploadContentImages(trans.getTId(), transContentImages[i]);
            }
        }

        mapper.update(trans);
    }
    // 운송 상품 업데이트 (끝)--------------------------------------------------------------------------------------------------

    // 운송 상품 삭제 (시작)-------------------------------------------------------------------------------------------------
    public void delete(Integer id) {

        // 운송 상품 삭제전 type 테이블 을 삭제
        transTypeMapper.deleteByTId(id);

        // 운송 상품 삭제전 mainimage 테이블 삭제, aws DB 파일도 삭제
        deleteMainImage(id);

        // 운송 상품 삭제전 contentimage 테이블 삭제, aws DB 파일도 삭제
        deleteContentImage(id);

        // 좋아요 한 것들도 삭제
        transLikeMapper.deleteByTransId(id);

        // 운송 상품 삭제 하기
        mapper.deleteById(id);
    }

    // 운송 상품 삭제전 contentimage 테이블 삭제, aws DB 파일도 삭제
    private void deleteContentImage(Integer id) {
        List<TransContentImages> transContentImages = contentImagesMapper.selectNameByTId(id);

        if(transContentImages != null){
            for (TransContentImages transContentImage : transContentImages){
                String key = "travel/trans/contentImages/" + id + "/" + transContentImage.getName();
                DeleteObjectRequest objectRequest = DeleteObjectRequest.builder()
                        .bucket(bucket)
                        .key(key)
                        .build();
                // 파일삭제 경로로 삭제
                s3.deleteObject(objectRequest);
            }
        }

        // 테이블 이미지 삭제
        contentImagesMapper.deleteByTId(id);
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
