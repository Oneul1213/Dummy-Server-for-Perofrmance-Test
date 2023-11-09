package com.example.dummy.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    // Spring security를 사용한 로그인 구현 시 사용
    // private final BCryptPasswordEncoder encoder;

    /**
     * loginId 중복 체크
     * 
     * @param loginId
     * @return 중복되면 true, 아니면 false
     */
    public boolean checkLoginIdDuplicate(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }


    /**
     * nickname 중복 체크
     * 
     * @param nickname
     * @return 중복되면 true, 아니면 false
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    /**
     * 회원가입 1
     * 비밀번호 암호화 없이 저장
     * 
     * @param req
     */
    public void join(JoinRequest req) {
        userRepository.save(req.toEntity());
    }
    
    /**
     * 회원가입2
     * 비밀번호 암호화
     * 
     * @param req
     */
    // public void join2(JoinRequest req) {
    //     userRepository.save(req.toEntity(encoder.encode(req.getPassword())));
    // }

    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByLoginId(req.getLoginId());

        // loginId와 일치하는 User가 없으면 null return
        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if (!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    /**
     * userId(Long)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * userId로 찾아온 User가 존재하면 User return
     */

     /**
     * loginId(String)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * loginId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * loginId로 찾아온 User가 존재하면 User return
     */
}
