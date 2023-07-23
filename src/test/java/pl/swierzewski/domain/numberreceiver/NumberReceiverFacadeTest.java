package pl.swierzewski.domain.numberreceiver;

import org.junit.jupiter.api.Test;
import pl.swierzewski.domain.AdjustableClock;
import pl.swierzewski.domain.numberreceiver.dto.InputNumbersResultDTO;
import pl.swierzewski.domain.numberreceiver.dto.TicketDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {

    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2023, 2, 15, 11, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberValidator(),
            new InMemoryNumberReceiverRepositoryTestImpl(),
            clock
    );

    @Test
    void shouldReturnSuccess_whenUserGaveSixNumbers() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        // when
        InputNumbersResultDTO result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.message()).isEqualTo("success");
    }

    @Test
    void shouldReturnSaveToDatabase_whenUserGaveSixNumbers() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        InputNumbersResultDTO result = numberReceiverFacade.inputNumbers(numbersFromUser);
        LocalDateTime drawDate = LocalDateTime.of(2023, 2, 15, 12, 0, 0);
        // when
        List<TicketDTO> ticketDTOs = numberReceiverFacade.userNumbers(drawDate);
        // then
        assertThat(ticketDTOs).contains(
                TicketDTO.builder()
                        .ticketId(result.ticketId())
                        .drawDate(result.drawDate())
                        .numbersFromUser(result.numbersFromUser())
                        .build()
        );
    }

    @Test
    void shouldReturnFailed_whenUserGaveAtLeastOneNumberOutOfRangeOf1to99() {
        // given
        Set<Integer> numbersFromUser = Set.of(100, 2, 3, 4, 5, 6);
        // when
        InputNumbersResultDTO result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    void shouldReturnFailed_whenUserGaveLessThanSixNumbers() {
        // given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5);
        // when
        InputNumbersResultDTO result = numberReceiverFacade.inputNumbers(numbersFromUser);
        // then
        assertThat(result.message()).isEqualTo("failed");
    }

    @Test
    void shouldReturnFailed_whenUserGaveMoreThanSixNumbers() {
    }
}