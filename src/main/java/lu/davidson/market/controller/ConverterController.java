package lu.davidson.market.controller;


import lu.davidson.market.json.Price;
import lu.davidson.market.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tools/converter")
public class ConverterController {


    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/amount")
    public Price convertPrice(@RequestParam("amount") double amount,
                              @RequestParam("base") String baseCurrency,
                              @RequestParam("target") String targetCurrency)  {
        return exchangeService.convertAmount( amount,  baseCurrency,  targetCurrency);
    }



}
