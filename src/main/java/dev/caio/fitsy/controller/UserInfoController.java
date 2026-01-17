package dev.caio.fitsy.controller;

import dev.caio.fitsy.dto.request.CreateUserInfoRequest;
import dev.caio.fitsy.dto.request.UpdatePesoRequest;
import dev.caio.fitsy.dto.response.UserInfoResponse;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {
    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).body(userInfoService.getUserInfo(user));
    }

    @PostMapping("/create")
    public ResponseEntity<UserInfoResponse> setUserInfo(@AuthenticationPrincipal User user, @RequestBody @Valid CreateUserInfoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userInfoService.createUserInfo(user, request));
    }

    @PutMapping("/update-peso")
    public ResponseEntity<UserInfoResponse> updatePeso(@AuthenticationPrincipal User user, @RequestBody @Valid UpdatePesoRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(userInfoService.updatePeso(user, request));
    }


}
