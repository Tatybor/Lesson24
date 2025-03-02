package ru.TT.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.TT.entity.UserOfLibrary;
import ru.TT.repository.UserOfLibraryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserOfLibraryDetailsService implements UserDetailsService {
    private final UserOfLibraryRepository userOfLibraryRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserOfLibrary userOfLibrary = userOfLibraryRepository.findUserOfLibraryByEmail(email);
        log.info("нашли пользователей библиотеки по мейлу");
        return org.springframework.security.core.userdetails.User.builder().username(userOfLibrary.getEmail())
                .password(userOfLibrary.getPassword())
                .roles(userOfLibrary.getRole().name())
                .build();
    }
}
