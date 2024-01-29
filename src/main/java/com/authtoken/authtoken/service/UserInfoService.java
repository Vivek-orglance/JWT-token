package com.authtoken.authtoken.service;

import com.authtoken.authtoken.dto.SignUpDto;
import com.authtoken.authtoken.dto.UserDTO;
import com.authtoken.authtoken.entity.UserInfo;
import com.authtoken.authtoken.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = userInfoRepository.findByEmail(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String signUp(SignUpDto signUpDto) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(encoder.encode(signUpDto.getPassword()));

        userInfo.setId(signUpDto.getId());
        userInfo.setFullName(signUpDto.getFullName());
        userInfo.setEmail(signUpDto.getEmail());
        userInfo.setMobileNumber(signUpDto.getMobileNumber());
        userInfo.setInvitationCode(signUpDto.getInvitationCode());
        userInfo.setRoles(signUpDto.getRoles());
        userInfo.setTermsCondition(signUpDto.isTermsCondition());

        userInfoRepository.save(userInfo);
        return "User registered successfully";
    }

    public String createUser(UserDTO userDTO) throws IOException {

        String userId = String.valueOf(userDTO.getId());
        UserInfo user = userInfoRepository.findById(userId).get();

        MultipartFile resumeFile = userDTO.getResume();
        MultipartFile avatarFile = userDTO.getAvatar();
        user.setResume(resumeFile.getBytes());
        user.setAvatar(avatarFile.getBytes());
        user.setCreatedDate(LocalDate.from(java.time.LocalDate.now()));

        userInfoRepository.save(user);
        return "user created successfully";

    }
}

