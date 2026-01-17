package dev.caio.fitsy.dto.mapper;

import dev.caio.fitsy.dto.request.CreateUserInfoRequest;
import dev.caio.fitsy.dto.response.UserInfoResponse;
import dev.caio.fitsy.model.UserInfo;

import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    public UserInfoResponse modelToResponse(Integer status, String mensagem, UserInfo user){
        UserInfoResponse response = new UserInfoResponse(status, mensagem, user.getPeso(), user.getAltura(), user.getSexo(), user.getDataNascimento());
        return response;
    }

    public UserInfo createRequestToModel(CreateUserInfoRequest request){
        UserInfo userInfo = new UserInfo();
        userInfo.setPeso(request.peso());
        userInfo.setAltura(request.altura());
        userInfo.setSexo(request.sexo());
        userInfo.setDataNascimento(request.data_nascimento());

        return userInfo;

    }
}
