package com.skkudteam3.skkusirenorder.common.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoOauth implements SocialOauth {
    @Override
    public String getOauthRedirectURL() {
        return null;
    }

//    //applications.yml 에서 value annotation을 통해서 값을 받아온다.
//    @Value("${spring.OAuth2.kakao.url}")
//    private String KAKAO_SNS_URL;
//
//    @Value("${spring.OAuth2.kakao.client-id}")
//    private String KAKAO_SNS_CLIENT_ID; // 앱 REST API 키
//
//    @Value("${spring.OAuth2.kakao.callback-login-url}")
//    private String KAKAO_SNS_CALLBACK_LOGIN_URL; // 인가 코드를 전달받을 서비스 서버의 URI
//
////    @Value("${spring.OAuth2.google.scope}")
////    private String GOOGLE_DATA_ACCESS_SCOPE; // 추가 항목 동의 받기 요청 시 사용
//
//    private final ObjectMapper objectMapper;
//    private final RestTemplate restTemplate;
//
//    // 1단계 : 인가코드 받기
//    // 1-1 : GET 요청
//    // 1-2 : 카카오 계정 로그인 요청 받음 -> 카카오 계정 로그인
//    // 1-3 : 동의 화면 받음 -> 동의하기
//    // 1-4 : 302 : Redirect URI 로 인가 코드 전달
//
//    // REDIRECT_URL를 생성하기 위한 메소드
//    @Override
//    public String getOauthRedirectURL() {
//
//        Map<String, Object> params = new HashMap<>();
////        params.put("scope", KAKAO_DATA_ACCESS_SCOPE);
//        params.put("response_type", "code");
//        params.put("client_id", KAKAO_SNS_CLIENT_ID);
//        params.put("redirect_uri", KAKAO_SNS_CALLBACK_LOGIN_URL);
//
//
//        //parameter를 형식에 맞춰 구성해주는 함수
//        String parameterString = params.entrySet().stream()
//                .map(x -> x.getKey() + "=" + x.getValue())
//                .collect(Collectors.joining("&"));
//        String redirectURL = KAKAO_SNS_URL + "?" + parameterString;
//        log.info("redirectURL = ", redirectURL);
//
//        return redirectURL;
//        /*
//         * https://accounts.google.com/o/oauth2/v2/auth?scope=profile&response_type=code
//         * https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}
//         * &client_id="할당받은 id"&redirect_uri="access token 처리")
//         * 로 Redirect URL을 생성하는 로직을 구성
//         * */
//    }
//
//
//    // 2단계 : 인가코드를 통해서 -> 토큰 받기
//    // 2-1 : POST /oauth/token
//    // 2-2 : 토큰 발급 받음.
//    public ResponseEntity<String> requestAccessToken(String code) {
//        String KAKAO_TOKEN_REQUEST_URL = "https://kauth.kakao.com/oauth/token";
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders(); // Header 설정. Content-type을 둬야함.
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("Accept", "application/json");
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // 본문 설정.
//        params.put("grant_type", Collections.singletonList("authorization_code")); // 고정.
//        params.put("client_id", Collections.singletonList(KAKAO_SNS_CLIENT_ID));
//        params.put("redirect_uri", Collections.singletonList(KAKAO_SNS_CALLBACK_LOGIN_URL));
//        params.put("code", Collections.singletonList(code));
////        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET); 선택적.
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
//
//        ResponseEntity<String> responseEntity=restTemplate.postForEntity(KAKAO_TOKEN_REQUEST_URL,
//                request,String.class);
//
//        if(responseEntity.getStatusCode() == HttpStatus.OK){
//            return responseEntity;
//        }
//        return null;
//    }
//
//    // JSON -> 토큰 가져오기
//    public KakaoOAuthToken getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
//        log.info("response.getBody() = {}", response.getBody());
//
//        KakaoOAuthToken kakaOAuthToken= objectMapper.readValue(response.getBody(),KakaoOAuthToken.class);
//        return kakaOAuthToken;
//    }
//
//
//    // 3단계 : 사용자 로그인 처리
//    // 3-1 : 발급 받은 토큰으로 사용자 정보 조회, 서비스 회원 정보 확인 또는 가입처리
//    // 3-2 : 로그인 완료
//    public ResponseEntity<String> requestUserInfo(KakaoOAuthToken oAuthToken) {
//        String KAKAO_USERINFO_REQUEST_URL="https://kapi.kakao.com/v2/user/me";
//
//        //header에 accessToken을 담는다.
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization","Bearer "+oAuthToken.getAccess_token());
//        headers.add("Content-type", "application/json;charset=utf-8");
//
//        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 카카오와 통신하게 된다.
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);
//        ResponseEntity<String> response = restTemplate.exchange(KAKAO_USERINFO_REQUEST_URL, HttpMethod.GET,request,String.class);
//
//        log.info("response.getBody() = {}", response.getBody());
//
//        return response;
//    }
//
//    public KakaoUser getUserInfo(ResponseEntity<String> userInfoRes) throws JsonProcessingException{
//        KakaoUser kakaoUser = objectMapper.readValue(userInfoRes.getBody(), KakaoUser.class);
//        log.info("kakaoUser = {}", kakaoUser.toString());
//        return kakaoUser;
//    }
}
