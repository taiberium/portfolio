package com.portfolio;

import com.portfolio.model.Instrument;
import com.portfolio.model.Position;
import com.portfolio.model.PositionChange;
import com.portfolio.model.Transaction;
import com.portfolio.repository.TransactionRepository;
import com.portfolio.service.PositionService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;


public class PositionServiceTest {

    private static final Instrument USD = new Instrument("USD", Instrument.InstrumentType.CASH);
    private static final Instrument GE = new Instrument("GE", Instrument.InstrumentType.SECURITY);

    private final TransactionRepository repository = Mockito.mock(TransactionRepository.class);
    private final PositionService service = new PositionService(repository);

    private void mockRepository(Transaction... transactions) {
        Flux<Transaction> flux = Flux.just(transactions);
        Mockito.when(repository.findAllByAccount(any())).thenReturn(flux);
    }

    @Test
    public void when_NoTransactions_thenEmptyFluxReturn() {
        Mockito.when(repository.findAllByAccount(any())).thenReturn(Flux.empty());

        Flux<Position> positionFlux = service.get(any());

        List<Position> positions = positionFlux.collectList().block();
        Assertions.assertThat(positions.size()).isEqualTo(0);
    }

    @Test
    public void when_2TransactionsSpendBy10Usd_then20UsdReturn() {
        PositionChange tenDollars = new PositionChange(USD, BigDecimal.TEN);
        Transaction transaction1 = Transaction.builder().firstSide(tenDollars).build();
        Transaction transaction2 = Transaction.builder().firstSide(tenDollars).build();
        mockRepository(transaction1,transaction2);

        Flux<Position> positionFlux = service.get(any());
        List<Position> positions = positionFlux.collectList().block();
        Position position = positions.get(0);

        Assertions.assertThat(position.getAmount()).isEqualTo(BigDecimal.TEN.add(BigDecimal.TEN));
        Assertions.assertThat(position.getInstrument()).isEqualTo(USD);
    }

    @Test
    public void when_1TransactionWithOnePositionChange_then10UsdReturn() {
        PositionChange tenDollars = new PositionChange(USD, BigDecimal.TEN);
        Transaction transaction = Transaction.builder().firstSide(tenDollars).build();
        mockRepository(transaction);

        Flux<Position> positionFlux = service.get(any());
        List<Position> positions = positionFlux.collectList().block();
        Position position = positions.get(0);

        Assertions.assertThat(position.getAmount()).isEqualTo(BigDecimal.TEN);
        Assertions.assertThat(position.getInstrument()).isEqualTo(USD);
    }

    @Test
    public void when_1TransactionsSpendBy10UsdFor1Ge_thenMinus10UsdAnd1GeReturn() {
        PositionChange tenDollars = new PositionChange(USD, BigDecimal.TEN.negate());
        PositionChange oneGe = new PositionChange(GE, BigDecimal.ONE);
        Transaction transaction = Transaction.builder().firstSide(oneGe).secondSide(tenDollars).build();
        mockRepository(transaction);

        Flux<Position> positionFlux = service.get(any());
        List<Position> positions = positionFlux.collectList().block();
        Map<Instrument,BigDecimal> position = positions.stream().collect(Collectors.toMap(Position::getInstrument, Position::getAmount));

        Assertions.assertThat(position.get(USD)).isEqualTo(BigDecimal.TEN.negate());
        Assertions.assertThat(position.get(GE)).isEqualTo(BigDecimal.ONE);
    }

}
