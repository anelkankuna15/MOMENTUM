package momentum.investment.momemtuminvestment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import momentum.investment.momemtuminvestment.model.Investor;
import momentum.investment.momemtuminvestment.service.InvestorService;

@RestController
@RequestMapping("/investors")
public class InvestorController {
    @Autowired
    private InvestorService investorService;

    @GetMapping
    public List<Investor> getAllInvestors() {
        return investorService.getAllInvestors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investor> getInvestorById(@PathVariable Long id) {
        Investor investor = investorService.getInvestorById(id);
        if (investor != null) {
            return ResponseEntity.ok(investor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Investor createInvestor(@RequestBody Investor investor) {
        return investorService.createInvestor(investor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investor> updateInvestor(@PathVariable Long id, @RequestBody Investor updatedInvestor) {
        updatedInvestor.setId(id);
        Investor updated = investorService.updateInvestor(updatedInvestor);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestor(@PathVariable Long id) {
        investorService.deleteInvestor(id);
        return ResponseEntity.noContent().build();
    }
}



