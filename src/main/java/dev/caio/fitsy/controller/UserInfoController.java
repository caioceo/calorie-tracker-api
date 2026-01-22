package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.mapper.UserInfoMapper;
import dev.caio.fitsy.dto.request.CreateUserInfoRequest;
import dev.caio.fitsy.dto.request.UpdatePesoRequest;
import dev.caio.fitsy.dto.response.UserInfoResponse;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.service.user.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final UserInfoMapper userInfoMapper;

    public UserInfoController(UserInfoService userInfoService, UserInfoMapper userInfoMapper) {
        this.userInfoService = userInfoService;
        this.userInfoMapper = userInfoMapper;
    }

    @GetMapping("")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(userInfoMapper.modelToResponse(HttpStatus.OK.value(), userInfoService.getUserInfo(user)));
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfoResponse> setUserInfo(@AuthenticationPrincipal User user, @RequestBody @Valid CreateUserInfoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userInfoMapper.modelToResponse(HttpStatus.CREATED.value(), userInfoService.createUserInfo(user, request)));
    }

    @PutMapping("/update-peso")
    public ResponseEntity<UserInfoResponse> updatePeso(@AuthenticationPrincipal User user, @RequestBody @Valid UpdatePesoRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(userInfoMapper.modelToResponse(HttpStatus.OK.value(), userInfoService.updatePeso(user, request)));
    }


}
