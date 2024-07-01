package ru.itpark.userservice.application.user.command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.userservice.domain.user.UserFactory;
import ru.itpark.userservice.domain.user.converters.LanguageConverter;
import ru.itpark.userservice.infrastructure.repositories.user.UserRepository;
import ru.itpark.userservice.presentation.web.user.contracts.FillUserDataCommand;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCommandService {
    private final UserRepository userRepository;
    private final LanguageConverter languageConverter;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(FillUserDataCommand fillUserDataCommand) {
        var user = UserFactory.createUser(fillUserDataCommand.fullName(),
                fillUserDataCommand.email(),
                fillUserDataCommand.login(),
                fillUserDataCommand.languages()
        );

        userRepository.saveUser(
                user.getFullName(),
                user.getEmail(),
                user.getLogin(),
                languageConverter.convertToDatabaseColumn(user.getLanguages()),
                user.getRole().toString(),
                user.getDateInfo().getCreatedAt(),
                user.getDateInfo().getDeletedAt()
        );
    }
}
