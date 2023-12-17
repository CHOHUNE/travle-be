package com.example.travelback.user.sms;

import com.example.travelback.user.dto.Member;
import com.example.travelback.user.mapper.MemberMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@Component
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class SmsUtil2 {
    @Value("${coolsms.api.key}")
    private String apikey;
    @Value("${coolsms.api.Secret}")
    private String SecretKey;

    private DefaultMessageService messageService;

    @PostConstruct
    public void initialize() {
        this.messageService = NurigoApp.INSTANCE.initialize(apikey, SecretKey, "https://api.coolsms.co.kr");
    }


    // ---------- 문자 발송 로직 ----------
    @PostMapping("sendSMS3")
    public ResponseEntity<String> sendOne(@RequestBody Member member) {

        System.out.println("전화번호: " + member.getUserPhoneNumber());
        System.out.println("예약번호: " + member.getMessageContent());

        if (messageService == null) {
            throw new IllegalStateException("초기화되었는지 확인");
        }

        Message message = new Message();
        message.setFrom("01036138304"); // 발신자 번호
        message.setTo(member.getUserPhoneNumber()); // 수신자 번호: 사용자가 입력한 번호
        // 메시지 내용: 판매자가 입력한 예약번호
        message.setText("[Web발신]\n트레블 여행\n구입하신 상품의 예약번호\n[" + member.getMessageContent() + "] 입니다.");

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return ResponseEntity.ok().build();
    }
}

