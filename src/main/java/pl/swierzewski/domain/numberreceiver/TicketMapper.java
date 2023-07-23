package pl.swierzewski.domain.numberreceiver;

import pl.swierzewski.domain.numberreceiver.dto.TicketDTO;

public class TicketMapper {

    public static TicketDTO mapFromTicket(Ticket ticket) {
        return TicketDTO.builder()
                .ticketId(ticket.ticketId())
                .numbersFromUser(ticket.numbersFromUser())
                .drawDate(ticket.drawDate())
                .build();
    }
}
