package pl.swierzewski.domain.numbergenerator;

import pl.swierzewski.domain.numbergenerator.dto.OneRandomNumberResponseDto;

public interface OneRandomNumberFetcher {
    OneRandomNumberResponseDto retrieveOneRandomNumber(int lowerBand, int upperBand);
}
