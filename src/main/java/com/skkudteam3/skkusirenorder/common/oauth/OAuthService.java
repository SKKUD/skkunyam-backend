package com.skkudteam3.skkusirenorder.common.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {
//    private final KakaoOauth kakaoOauth;
//    private final HttpServletResponse response;
//    private final UserService userService;
//    private final JwtService jwtService;
//
//
//    public String accessRequest(Constant.SocialLoginType socialLoginType) throws IOException {
//        String redirectURL;
//        switch (socialLoginType){ //각 소셜 로그인을 요청하면 소셜로그인 페이지로 리다이렉트 해주는 프로세스이다.
//            case KAKAO:{
//                redirectURL= kakaoOauth.getOauthRedirectURL();
//            }break;
////            case GOOGLE:{
////                redirectURL= googleOauth.getOauthRedirectURL();
////            }break;
//            default:{
//                throw new BaseException(INVALID_OAUTH_TYPE);
//            }
//
//        }
//
//        return redirectURL;
//    }
//
//
//    public GetSocialOAuthRes oAuthLoginOrJoin(Constant.SocialLoginType socialLoginType, String code) throws IOException {
//
//        switch (socialLoginType) {
//            case KAKAO: {
//                //카카오로 일회성 인가 코드를 보내 액세스 토큰이 담긴 응답객체를 받아옴
//                ResponseEntity<String> accessTokenResponse = kakaoOauth.requestAccessToken(code);
//                //응답 객체가 JSON형식으로 되어 있으므로, 이를 deserialization해서 자바 객체에 담을 것이다.
//                KakaoOAuthToken oAuthToken = kakaoOauth.getAccessToken(accessTokenResponse);
//
//                //액세스 토큰을 다시 카카오로 보내 카카오에 저장된 사용자 정보가 담긴 응답 객체를 받아온다.
//                ResponseEntity<String> userInfoResponse = kakaoOauth.requestUserInfo(oAuthToken);
//                //다시 JSON 형식의 응답 객체를 자바 객체로 역직렬화한다.
//                KakaoUser kakaoUser = kakaoOauth.getUserInfo(userInfoResponse);
//
//                //우리 서버의 db와 대조하여 해당 user가 존재하는 지 확인한다.
//                if(userService.checkUserByUserID(kakaoUser.getKakaoAccount().getEmail())) { // user가 DB에 있다면, 로그인 진행
//                    // 유저 정보 조회 (사용자 이름으로)
//                    GetUserRes getUserRes = userService.getUserByUserID(kakaoUser.getKakaoAccount().getEmail());
//
//                    //서버에 user가 존재하면 앞으로 회원 인가 처리를 위한 jwtToken을 발급한다.
//                    String jwtToken = jwtService.createJwt(getUserRes.getUserIdx());
//
//                    //액세스 토큰과 jwtToken, 이외 정보들이 담긴 자바 객체를 다시 전송한다.
//                    GetSocialOAuthRes getSocialOAuthRes = new GetSocialOAuthRes(jwtToken, getUserRes.getUserIdx(), oAuthToken.getAccess_token(), oAuthToken.getToken_type());
//                    return getSocialOAuthRes;
//                }else { // user가 DB에 없다면, 회원가입 진행
//                    // 유저 정보 저장
//                    PostUserRes postUserRes = userService.createOAuthUser(kakaoUser.toEntity());
//                    GetSocialOAuthRes getSocialOAuthRes = new GetSocialOAuthRes(postUserRes.getJwt(), postUserRes.getUserIdx(), oAuthToken.getAccess_token(), oAuthToken.getToken_type());
//                    return getSocialOAuthRes;
//                }
//            }
//            default: {
//                throw new BaseException(INVALID_OAUTH_TYPE);
//            }
//
//        }
//    }


}
