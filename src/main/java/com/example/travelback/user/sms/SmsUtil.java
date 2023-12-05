package com.example.travelback.user.sms;

import com.example.travelback.user.dto.Member;
import com.example.travelback.user.mapper.MemberMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.NurigoApp;
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
public class SmsUtil {
    @Value("${coolsms.api.key}")
    private String apikey;
    @Value("${coolsms.api.Secret}")
    private String SecretKey;

    private DefaultMessageService messageService;
    private final MemberMapper mapper;

    // ---------- 난수 저장 로직 ----------
    private Map<String, String> verificationCodes = new HashMap<>();


    @PostConstruct // Bean 생성 후 initialize 메소드를 자동으로 호출해줌 , 해당 빈의 초기화 작업까지 수행
    public void initialize() {
        this.messageService = NurigoApp.INSTANCE.initialize(apikey, SecretKey, "https://api.coolsms.co.kr");
    }

    // ---------- 문자 발송 로직 ----------
    @PostMapping("sendSMS")
    public ResponseEntity<String> sendOne(@RequestBody Member member) {
        if (messageService == null) {
            throw new IllegalStateException("초기화되었는지 확인");
        }

        // ---------- 6자리의 난수 생성 ----------
        String verificationCode = generateVerificationCode();

        // ---------- 난수를 메모리에 저장 로직 ----------
        verificationCodes.put(member.getUserPhoneNumber(), verificationCode);

//        Message message = new Message();
//        message.setFrom("01036138304");
//        message.setTo(member.getUserPhoneNumber());
//        message.setText("[Web발신]\n회원가입 인증번호\n[" + verificationCode + "]를 입니다.");

        System.out.println(verificationCode);

//        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
//        System.out.println(response);

        return ResponseEntity.ok().build();
    }

    // ---------- 난수 생성 로직 ----------
    private String generateVerificationCode() {
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        return numStr.toString();
    }

    // ---------- 인증번호 저장 / 비교 로직 (아이디, 패스워드 찾기용) ----------
    @PostMapping("sendSmsOk")
    public ResponseEntity<Map<String,Object>> verifySMS(@RequestBody Member request) {
        // verificationCodes : 생성된 난수를 보낸 핸드폰번호랑 매치해서 storedCode에 저장
        String storedCode = verificationCodes.get(request.getUserPhoneNumber());

        // 생성해서 저장한 난수와, 프론트에서 적은 난수랑 비교
        if (storedCode != null && storedCode.equals(request.getVerificationCode())) {

            // id 불러오는 로직
            String id = mapper.selectByUserNameAndId(request.getUserPhoneNumber());
            // 성공한 경우 저장된 난수 삭제
            verificationCodes.remove(request.getUserPhoneNumber());

            return ResponseEntity.ok(Map.of("message", "본인인증 성공", "id", id));
        }
//        else {
//            // 본인인증 실패
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<>());
//        }
        return null;
    }

    // ---------- 인증번호 저장 / 비교 로직 (회원가입용) ----------
    @PostMapping("sendSmsOk2")
    public ResponseEntity<String> verifySMS2(@RequestBody Member request) {
        // verificationCodes : 생성된 난수를 보낸 핸드폰번호랑 매치해서 storedCode에 저장
        String storedCode = verificationCodes.get(request.getUserPhoneNumber());

        // 생성해서 저장한 난수와, 프론트에서 적은 난수랑 비교
        if (storedCode != null && storedCode.equals(request.getVerificationCode())) {

            // 성공한 경우 저장된 난수 삭제
            verificationCodes.remove(request.getUserPhoneNumber());
            return ResponseEntity.ok("본인인증 성공");
        } else {
            // 본인인증 실패
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("본인인증 실패");
        }
    }
}

