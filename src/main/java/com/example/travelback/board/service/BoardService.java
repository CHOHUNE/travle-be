package com.example.travelback.board.service;

import com.example.travelback.board.domain.Board;
import com.example.travelback.board.domain.BoardFile;
import com.example.travelback.board.mapper.BoardMapper;
import com.example.travelback.board.mapper.FileMapper;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;
    private final FileMapper fileMapper;

    private final S3Client s3;

    @Value("${aws.s3.bucket.name}")
    private String bucket;

    @Value("${image.file.prefix}")
    private String urlPrefix;

    public boolean add(Board board, MultipartFile[] files, Member login) throws IOException {
        board.setWriter(login.getUserId());
        System.out.println("files = " + files);

        int cnt = mapper.add(board);

        if (files!=null){
            for (int i = 0; i < files.length; i++) {
                fileMapper.insert(board.getId(), files[i].getOriginalFilename());

                upload(board.getId(),files[i]);
            }
        }

        return  cnt==1;
    }


    private void upload(Integer boardId, MultipartFile file) throws IOException {

        String key= "travel/board/"+boardId+"/"+file.getOriginalFilename();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        s3.putObject(objectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

    }

    public boolean validate( Board board){

        if (board==null){
            return  false;
        }
        if (board.getContent()==null||board.getContent().isBlank()){
            return false;
        }
        if (board.getWriter()==null ||board.getWriter().isBlank()){
            return false;
        }
        if (board.getTitle()==null||board.getTitle().isBlank()){
            return false;
        }
        return true;


    }


    public Map<String, Object> list(Integer page, String keyword) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pageInfo = new HashMap<>();

        int countAll = mapper.countAll("%"+keyword+"%");
        int lastPageNumber = (countAll - 1) / 5 + 1;
        int startPageNumber = (page - 1) / 5 * 5 + 1;
        int endPageNumber = startPageNumber +4;
        endPageNumber = Math.min(endPageNumber, lastPageNumber);
        int prevPageNumber = startPageNumber - 5;
        int nextPageNumber = endPageNumber + 1;

        pageInfo.put("currentPageNumber", page);
        pageInfo.put("startPageNumber", startPageNumber);
        pageInfo.put("endPageNumber", endPageNumber);
        if (prevPageNumber > 0) {
            pageInfo.put("prevPageNumber", prevPageNumber);
        }
        if (nextPageNumber <= lastPageNumber) {
            pageInfo.put("nextPageNumber", nextPageNumber);
        }

        int from = (page - 1) * 5;
        map.put("boardList", mapper.selectAll(from, "%"+ keyword +"%"));
        map.put("pageInfo", pageInfo);
        return map;
    }




    public Board id(Integer id) {
        Board board = mapper.id(id);

        List<BoardFile> boardFiles = fileMapper.selectNamesByBoardId(id);

        for (BoardFile boardFile : boardFiles){
            String url=urlPrefix+"travel/board/"+id+"/"+boardFile.getName();
            boardFile.setUrl(url);
        }

        board.setFiles(boardFiles);

        return board;


    }

    public boolean remove(Integer id) {
      return mapper.remove(id)==1;
    }

    public boolean update(Board board) {
        return mapper.update(board) == 1;
    }
    public boolean hasAccess(Integer id, Member login) {
        Board board= mapper.selectById(id);
        return board.getWriter().equals(login.getUserId());

    }


}
