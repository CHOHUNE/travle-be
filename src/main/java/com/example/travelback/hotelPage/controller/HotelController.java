package com.example.travelback.hotelPage.controller;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.Reservation;
import com.example.travelback.hotelPage.service.HotelService;
import com.example.travelback.hotelPage.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {


    private final HotelService hotelService;
    private final ReservationService reservationService;


    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotel = hotelService.getAllHotels();
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping("/reserv/id/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/edit/id/{id}")
    public ResponseEntity<Hotel> getEditHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }


    @PostMapping(value = "/write")
    public void add(Hotel hotel,
                    @RequestParam(value = "mainImg[]", required = false) MultipartFile mainImg,
                    @RequestParam(value = "subImg1[]", required = false) MultipartFile subImg1,
                    @RequestParam(value = "subImg2[]", required = false) MultipartFile subImg2,
                    @RequestParam(value = "mapImg[]", required = false) MultipartFile mapImg
    ) throws IOException {

        if (mainImg != null) {
            System.out.println("mainImg" + mainImg.getOriginalFilename());
            System.out.println("maingImg" + mainImg.getSize());
        }

        hotelService.addHotel(hotel, mainImg,subImg1,subImg2,mapImg);
    }


    @PutMapping(value = "/edit")
    public void edit(Hotel hotel,
                     @RequestParam(value = "removeFileId[]", required = false) Integer removeFileId,
                     @RequestParam(value = "uploadFile[]", required = false) MultipartFile uploadFile
    ) throws IOException {


        System.out.println("Hotel ID: " + hotel.getHid());

        if (removeFileId != null) {
            System.out.println("Remove File ID: " + removeFileId);
        }

        if (uploadFile != null) {
            System.out.println("Upload File: " + uploadFile.getOriginalFilename());
            System.out.println("Upload File Size: " + uploadFile.getSize());
        }

        // HotelService를 통해 업데이트 수행
        hotelService.update(hotel, removeFileId, uploadFile);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        hotelService.deleteHotel(id);
    }

    @GetMapping("/pay/{id}")
    public ResponseEntity<Hotel> getPayHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);

        return ResponseEntity.ok(hotel);
    }

    @PostMapping("/pay")
    public void add(@RequestBody Reservation reservation) {
        reservationService.addResrvation(reservation);

    }
}


