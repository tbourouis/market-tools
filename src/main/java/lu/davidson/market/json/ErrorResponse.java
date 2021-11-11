package lu.davidson.market.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorResponse {
    public final String errorMessage;
}
