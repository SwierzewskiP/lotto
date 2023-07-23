package pl.swierzewski.domain.numberreceiver;

import java.util.Set;

class NumberValidator {

    private static final int MAX_NUMBERS_FROM_USER = 6;
    private static final int SMALLEST_NUMBER_FROM_USER = 1;
    private static final int BIGGEST_NUMBER_FROM_USER = 99;

    boolean areAllNumbersInRange(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .filter(number -> number >= SMALLEST_NUMBER_FROM_USER)
                .filter(number -> number <= BIGGEST_NUMBER_FROM_USER)
                .count() == MAX_NUMBERS_FROM_USER;
    }
}
