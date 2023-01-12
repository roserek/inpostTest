package pl.inpost.test.service.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.inpost.test.repository.ParametrizationRepository;
import pl.inpost.test.service.order.compute.AmountDiscountDecorator;
import pl.inpost.test.service.order.compute.BasicComputePriceService;
import pl.inpost.test.service.order.compute.ComputePriceService;
import pl.inpost.test.service.order.compute.PercentageDiscountDecorator;

import java.util.List;


/**
 * I assumed the promotions don't combine. :)
 * If percentage discount is available then amount discount is omitted.
 * I know that this is not ideal solution, but I wanted to use factory pattern and this was the simplest way as came to my mind ;)
 */
@AllArgsConstructor
@Component
public class ComputePriceServiceFactory {
    private final ParametrizationRepository parametrizationRepository;
    private final List<ComputePriceService> computePriceServiceList;

    public ComputePriceService getComputePriceService() {
        if(Boolean.TRUE.equals(parametrizationRepository.getPercentageDiscountEnable())) {
            return getService(PercentageDiscountDecorator.class);
        } else if(Boolean.TRUE.equals(parametrizationRepository.getAmountDiscountEnable())) {
            return getService(AmountDiscountDecorator.class);
        } else {
            return getService(BasicComputePriceService.class);
        }
    }

    private <T> T getService(Class<T> clazz) {
        Object object = computePriceServiceList.stream()
                .filter(service -> service.getClass().equals(clazz))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Implementation %s of ComputePriceService not found.", clazz.getName())));

        return (T) object;
    }

}
