package de.htwberlin.webtech.einundzwanzig.service;

import de.htwberlin.webtech.einundzwanzig.persistence.UserEntity;
import de.htwberlin.webtech.einundzwanzig.persistence.UserRepository;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests implements WithAssertions {

    @InjectMocks
    private UserService underTest;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("should return true if delete was succesful")
    void returnTrueIfDeleteWasSuccessfull() {
        Long givenId = 1L;
        doReturn(true).when(repository).existsById(givenId);

        boolean result = underTest.deleteById(givenId);

        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("should return false if person to delete does not exist")
    void returnFalseIfPersonNotExist() {
        Long givenId = 1L;
        doReturn(false).when(repository).existsById(givenId);

        boolean result = underTest.deleteById(givenId);

        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("should transform UserEntity to User")
    void shouldTransformUserEntityToUser() {
        var userEntity = Mockito.mock(UserEntity.class);
        doReturn(1L).when(userEntity).getId();
        doReturn("Player").when(userEntity).getUsername();
        doReturn(10).when(userEntity).getCoins();
        doReturn(5).when(userEntity).getWins();
        doReturn(5).when(userEntity).getLosses();
        doReturn(5).when(userEntity).getDraws();

        var result = underTest.transformEntity(userEntity);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getUsername()).isEqualTo("Player");
        assertThat(result.getCoins()).isEqualTo(10);
        assertThat(result.getWins()).isEqualTo(5);
        assertThat(result.getLosses()).isEqualTo(5);
        assertThat(result.getDraws()).isEqualTo(5);
    }
}
