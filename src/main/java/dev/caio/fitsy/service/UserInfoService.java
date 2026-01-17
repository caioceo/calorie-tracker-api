package dev.caio.fitsy.service;
import dev.caio.fitsy.dto.mapper.UserInfoMapper;
import dev.caio.fitsy.dto.request.CreateUserInfoRequest;
import dev.caio.fitsy.dto.request.UpdatePesoRequest;
import dev.caio.fitsy.dto.response.UserInfoResponse;
import dev.caio.fitsy.exceptions.AlreadyExistsException;
import dev.caio.fitsy.exceptions.BusinessException;
import dev.caio.fitsy.exceptions.NotFoundException;
import dev.caio.fitsy.model.User;
import dev.caio.fitsy.model.UserInfo;
import dev.caio.fitsy.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;
    private final HistoricoPesoService historicoPesoService;

    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper, HistoricoPesoService historicoPesoService) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoMapper = userInfoMapper;
        this.historicoPesoService = historicoPesoService;
    }

    @Transactional
    public UserInfoResponse createUserInfo(User user, CreateUserInfoRequest request){

        if(user.getUserInfo() != null){
            throw new AlreadyExistsException("UserInfo");
        }


        UserInfo info = userInfoMapper.createRequestToModel(request);
        info.setUser(user);
        userInfoRepository.save(info);
        historicoPesoService.appendNewPeso(info);

        return userInfoMapper.modelToResponse(HttpStatus.CREATED.value(), "Informações do usuário salvas com sucesso!", info);

    }

    public UserInfoResponse getUserInfo(User user){
        UserInfo info = userInfoRepository.findByUser(user);
        if(info == null){
            throw new NotFoundException("UserInfo", "user", user.getId());
        }

        return userInfoMapper.modelToResponse(HttpStatus.OK.value(), "Informações do usuário encontradas com sucesso!", info);

    }

    @Transactional
    public UserInfoResponse updatePeso(User user, UpdatePesoRequest request){
        UserInfo info = userInfoRepository.findByUser(user);

        if(info == null){
            throw new NotFoundException("UserInfo", "user", user.getId());
        }

        if(info.getPeso().equals(request.peso())){
            throw new BusinessException("O peso informado é igual ao peso atual.");
        }

        info.setPeso(request.peso());
        userInfoRepository.save(info);
        historicoPesoService.appendNewPeso(info);

        return userInfoMapper.modelToResponse(HttpStatus.OK.value(), "Peso atualizado com sucesso!", info);

    }
}
