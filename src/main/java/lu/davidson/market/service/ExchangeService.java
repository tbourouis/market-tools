package lu.davidson.market.service;

import lu.davidson.market.exception.InvalidInputException;
import lu.davidson.market.json.Price;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class  ExchangeService {
    private static final Map<String, Double> euroRates;

    static {
        euroRates = new HashMap<>();
        euroRates.put("EUR", 1.0);
        euroRates.put("USD", 1.16);
        euroRates.put("JPY", 130.83);
        euroRates.put("CAD", 1.44);
        euroRates.put("CHF", 1.06);
    }

    public Price convertAmount(double amount, String base, String target) {
        if (!euroRates.containsKey(base) || !euroRates.containsKey(target)) {
            throw new InvalidInputException("At least one wrong input");
        }
        try {
            double euroAmount = convertToEuro(amount, base);
            Double rate = euroRates.get(target);
            if (rate == null) {
                throw new InvalidInputException("At least one input have an empty rate");
            }
            return new Price(euroAmount * rate, target);
        }catch (ArithmeticException exp){
            throw new InvalidInputException("Wrong rate setup");
        }
    }

    private double convertToEuro(double amount, String base) throws ArithmeticException{
        return amount / euroRates.get(base);
    }
}
