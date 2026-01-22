package dev.caio.fitsy.service.user;
import dev.caio.fitsy.dto.mapper.UserInfoMapper;
import dev.caio.fitsy.dto.request.CreateMetaRequest;
import dev.caio.fitsy.dto.request.CreateUserInfoRequest;
import dev.caio.fitsy.dto.request.UpdatePesoRequest;
import dev.caio.fitsy.exceptions.AlreadyExistsException;
import dev.caio.fitsy.exceptions.BusinessException;
import dev.caio.fitsy.exceptions.NotFoundException;
import dev.caio.fitsy.model.enums.Estrategia;
import dev.caio.fitsy.model.enums.NivelAtividade;
import dev.caio.fitsy.model.enums.Objetivo;
import dev.caio.fitsy.model.metas.Meta;
import dev.caio.fitsy.model.user.User;
import dev.caio.fitsy.model.user.UserInfo;
import dev.caio.fitsy.repository.metas.MetaRepository;
import dev.caio.fitsy.repository.user.UserInfoRepository;
import dev.caio.fitsy.repository.user.UserRepository;
import dev.caio.fitsy.service.metas.MetaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserInfoMapper userInfoMapper;
    private final HistoricoPesoService historicoPesoService;
    private final MetaService metaService;
    private final UserRepository userRepository;
    private final MetaRepository metaRepository;


    public UserInfoService(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper, HistoricoPesoService historicoPesoService, MetaService metaService,
                           UserRepository userRepository,
                           MetaRepository metaRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userInfoMapper = userInfoMapper;
        this.historicoPesoService = historicoPesoService;
        this.metaService = metaService;
        this.userRepository = userRepository;
        this.metaRepository = metaRepository;
    }

    @Transactional
    public UserInfo createUserInfo(User user, CreateUserInfoRequest request){

        if(user.getUserInfo() != null){
            throw new AlreadyExistsException("UserInfo");
        }

        UserInfo userInfo = userInfoMapper.createRequestToModel(request);
        userInfo.setUser(user);
        userInfoRepository.save(userInfo);
        historicoPesoService.appendNewPeso(userInfo);
        user.setUserInfo(userInfo);
        userInfoRepository.save(userInfo);

        Meta meta = metaService.createNewMeta(user, new CreateMetaRequest(
                Objetivo.MANTER_PESO,
                Estrategia.CONSERVADORA,
                userInfo.getPeso(),
                NivelAtividade.MODERADO
        ));


        return userInfo;
    }

    public UserInfo getUserInfo(User user){
        UserInfo userInfo = userInfoRepository.findByUser(user);
        if(userInfo == null){
            throw new NotFoundException("UserInfo", "user", user.getId());
        }

        return userInfo;
    }

    @Transactional
    public UserInfo updatePeso(User user, UpdatePesoRequest request){
        UserInfo userInfo = userInfoRepository.findByUser(user);

        if(userInfo == null){
            throw new NotFoundException("UserInfo", "user", user.getId());
        }

        if(userInfo.getPeso().equals(request.peso())){
            throw new BusinessException("O peso userInformado é igual ao peso atual.");
        }

        userInfo.setPeso(request.peso());
        userInfoRepository.save(userInfo);
        historicoPesoService.appendNewPeso(userInfo);

        return userInfo;
    }
}
