package pl.swierzewski.domain.resultchecker;

import pl.swierzewski.domain.numbergenerator.WinningNumbersGeneratorFacade;
import pl.swierzewski.domain.numberreceiver.NumberReceiverFacade;


public class ResultCheckerConfiguration {

    ResultCheckerFacade createForTest(WinningNumbersGeneratorFacade generatorFacade, NumberReceiverFacade receiverFacade, PlayerRepository playerRepository) {
        WinnersRetriever winnerGenerator = new WinnersRetriever();
        return new ResultCheckerFacade(generatorFacade, receiverFacade, playerRepository, winnerGenerator);
    }
}
