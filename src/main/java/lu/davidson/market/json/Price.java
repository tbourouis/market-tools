package lu.davidson.market.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Price {
    private double amount;
    private String currency;
}

