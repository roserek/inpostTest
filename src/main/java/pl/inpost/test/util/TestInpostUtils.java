package pl.inpost.test.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class TestInpostUtils {

    public static BigDecimal multiplyPrice(BigDecimal price, Integer amount) {
        if(Objects.isNull(price) || Objects.isNull(amount))
            return null;
        return price.multiply(BigDecimal.valueOf(amount)).setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal computePercentage(BigDecimal base, Integer percentage){
        if(Objects.isNull(base)) return null;
        if(Objects.isNull(percentage)) return base;

        return base.multiply(BigDecimal.valueOf(percentage)).divide(new BigDecimal(100), RoundingMode.HALF_UP);
    }

}
