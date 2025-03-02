package ru.TT.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.TT.DTO.UserOfLibraryDTO;
import ru.TT.entity.UserOfLibrary;
import ru.TT.repository.UserOfLibraryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserOfLibraryServiceImpl implements UserOfLibraryService {
    @Autowired
    UserOfLibraryRepository userOfLibraryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserOfLibraryDTO addNewUser(UserOfLibrary userOfLibrary) {
        String passwordEncode = passwordEncoder.encode(userOfLibrary.getPassword());
        userOfLibrary.setPassword(passwordEncode);
        UserOfLibrary newUserOfLibrary = userOfLibraryRepository.save(userOfLibrary);
        UserOfLibraryDTO userOfLibraryDTO = new ObjectMapper().convertValue(newUserOfLibrary, UserOfLibraryDTO.class);
        log.info("добавили нового юзера через ДТО");
        return userOfLibraryDTO;
    }

















    @Override
    public UserOfLibraryDTO getUserOfLibraryByEmail(String email) {
        UserOfLibrary userOfLibrary = userOfLibraryRepository.findUserOfLibraryByEmail(email);
        return new ObjectMapper().convertValue(userOfLibrary, UserOfLibraryDTO.class);
    }

}










