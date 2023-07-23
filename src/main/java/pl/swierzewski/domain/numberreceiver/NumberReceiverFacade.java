package pl.swierzewski.domain.numberreceiver;

/*
 * klient podaje 6 liczb
 * liczby muszą być w zakresie 1-99
 * liczby nie mogą się powtarzać
 * klient dostaje informację o dacie losowania
 * klient dostaje swój indywidualny identyfikator losowania
 */

import lombok.AllArgsConstructor;
import pl.swierzewski.domain.numberreceiver.dto.InputNumbersResultDTO;
import pl.swierzewski.domain.numberreceiver.dto.TicketDTO;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator validator;
    private final NumberReceiverRepository repository;
    private final Clock clock;

    public InputNumbersResultDTO inputNumbers(Set<Integer> numbersFromUser) {
        boolean inRange = validator.areAllNumbersInRange(numbersFromUser);
        if (inRange) {
            String ticketId = UUID.randomUUID().toString();
            LocalDateTime drawDate = LocalDateTime.now(clock);
            Ticket savedTicket = repository.save(new Ticket(ticketId, drawDate, numbersFromUser));
            return InputNumbersResultDTO.builder()
                    .drawDate(savedTicket.drawDate())
                    .ticketId(savedTicket.ticketId())
                    .numbersFromUser(numbersFromUser)
                    .message("success")
                    .build();
        }
        return InputNumbersResultDTO.builder()
                .message("failed")
                .build();
    }

    public List<TicketDTO> userNumbers(LocalDateTime date) {
        List<Ticket> allTicketsByDrawDate = repository.findAllTicketsByDrawDate(date);
        return allTicketsByDrawDate.stream()
                .map(TicketMapper::mapFromTicket)
                .toList();
    }
}