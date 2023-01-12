package pl.inpost.test.service.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.inpost.test.repository.ParametrizationRepository;
import pl.inpost.test.service.order.compute.AmountDiscountDecorator;
import pl.inpost.test.service.order.compute.BasicComputePriceService;
import pl.inpost.test.service.order.compute.ComputePriceService;
import pl.inpost.test.service.order.compute.PercentageDiscountDecorator;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComputePriceServiceFactoryTest {
    @Mock
    private ParametrizationRepository parametrizationRepository;

    private ComputePriceServiceFactory computePriceServiceFactory;

    @BeforeEach
    public void init() {
        List<ComputePriceService> computePriceServiceList = List.of(new AmountDiscountDecorator(null, null), new PercentageDiscountDecorator(null, null), new BasicComputePriceService());
        computePriceServiceFactory = new ComputePriceServiceFactory(parametrizationRepository, computePriceServiceList);
    }

    @Test
    public void percentageDiscountEnabledTest() {
        when(parametrizationRepository.getPercentageDiscountEnable()).thenReturn(Boolean.TRUE);

        ComputePriceService computePriceService = computePriceServiceFactory.getComputePriceService();

        Assertions.assertNotNull(computePriceService);
        Assertions.assertTrue(computePriceService instanceof PercentageDiscountDecorator);
    }

    @Test
    public void amountDiscountEnabledTest() {
        when(parametrizationRepository.getPercentageDiscountEnable()).thenReturn(Boolean.FALSE);
        when(parametrizationRepository.getAmountDiscountEnable()).thenReturn(Boolean.TRUE);

        ComputePriceService computePriceService = computePriceServiceFactory.getComputePriceService();

        Assertions.assertNotNull(computePriceService);
        Assertions.assertTrue(computePriceService instanceof AmountDiscountDecorator);
    }

    @Test
    public void basicDiscountEnabledTest() {
        when(parametrizationRepository.getPercentageDiscountEnable()).thenReturn(Boolean.FALSE);
        when(parametrizationRepository.getAmountDiscountEnable()).thenReturn(Boolean.FALSE);

        ComputePriceService computePriceService = computePriceServiceFactory.getComputePriceService();

        Assertions.assertNotNull(computePriceService);
        Assertions.assertTrue(computePriceService instanceof BasicComputePriceService);
    }
}
