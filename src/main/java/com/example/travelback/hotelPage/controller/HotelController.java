package com.example.travelback.hotelPage.controller;


import com.example.travelback.hotelPage.domain.Hotel;
import com.example.travelback.hotelPage.domain.Like;
import com.example.travelback.hotelPage.domain.Reservation;
import com.example.travelback.hotelPage.mapper.LikeMapper;
import com.example.travelback.hotelPage.service.HotelService;
import com.example.travelback.hotelPage.service.LikeService;
import com.example.travelback.hotelPage.service.ReservationService;
import com.example.travelback.user.dto.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hotel")
@RequiredArgsConstructor
public class HotelController {


    private final HotelService hotelService;
    private final ReservationService reservationService;
    private final LikeService likeService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotel = hotelService.getAllHotels();

        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping("/bucket/id/{userId}")
    public List<Like> getLikeByUserId (@PathVariable String userId){

        return likeService.getLikeByUserId(userId);
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
            System.out.println("mainImgFileName:" + mainImg.getOriginalFilename());
            System.out.println("subImg1 = " + subImg1.getName());
            System.out.println("subImg2 = " + subImg2.getName());
            System.out.println("mapImg = " + mapImg.getName());

        }

        hotelService.addHotel(hotel,mainImg,subImg1,subImg2,mapImg);
    }


    @PutMapping(value = "/edit")
    public void edit(Hotel hotel,
                     @RequestParam(value = "hid", required = false) Integer hid,
                     @RequestParam(value = "mainImg[]", required = false) MultipartFile mainImg,
                     @RequestParam(value = "subImg1[]", required = false) MultipartFile subImg1,
                     @RequestParam(value = "subImg2[]", required = false) MultipartFile subImg2,
                     @RequestParam(value = "mapImg[]", required = false) MultipartFile mapImg
    ) throws IOException {


        hotelService.update(hotel, hid,mainImg,subImg1,subImg2,mapImg);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        hotelService.deleteHotel(id);
    }

    @GetMapping("/pay/{id}")
    public ResponseEntity<Map<String, Object>> getPayHotelById(@PathVariable Long id, @SessionAttribute(value = "login", required = false) Member login) {
        Hotel hotel = hotelService.getHotelById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", login.getUserName());
        map.put("email", login.getUserEmail());
        map.put("phoneNumber", login.getUserPhoneNumber());



        return ResponseEntity.ok(Map.of("hotel", hotel, "member", map));

    }

    @PostMapping("/pay")
    public void add(@RequestBody Reservation reservation) {
        reservationService.addResrvation(reservation);
    }
}


