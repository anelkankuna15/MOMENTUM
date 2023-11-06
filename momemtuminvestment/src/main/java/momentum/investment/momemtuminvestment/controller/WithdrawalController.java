package momentum.investment.momemtuminvestment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import momentum.investment.momemtuminvestment.model.Withdrawal;
import momentum.investment.momemtuminvestment.service.WithdrawalService;
import momentum.investment.momemtuminvestment.validator.WithdrawalValidator;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @GetMapping
    public List<Withdrawal> getAllWithdrawals() {
        return withdrawalService.getAllWithdrawals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Withdrawal> getWithdrawalById(@PathVariable Long id) {
        Withdrawal withdrawal = withdrawalService.getWithdrawalById(id);
        if (withdrawal != null) {
            return ResponseEntity.ok(withdrawal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createWithdrawal(@RequestBody Withdrawal withdrawal) {
        if (!WithdrawalValidator.isInvestorEligibleForRetirementWithdrawal(withdrawal)) {
            return ResponseEntity.badRequest().body("Investor's age must be greater than 65 for retirement product withdrawals.");
        }

        if (!WithdrawalValidator.isWithdrawalAmountValid(withdrawal)) {
            return ResponseEntity.badRequest().body("Invalid withdrawal request");
        }

        Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(withdrawal);

        if (createdWithdrawal != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdWithdrawal);
        } else {
            return ResponseEntity.badRequest().body("Invalid withdrawal request");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Withdrawal> updateWithdrawal(@PathVariable Long id, @RequestBody Withdrawal updatedWithdrawal) {
        updatedWithdrawal.setId(id);
        Withdrawal updated = withdrawalService.updateWithdrawal(updatedWithdrawal);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable Long id) {
        withdrawalService.deleteWithdrawal(id);
        return ResponseEntity.noContent().build();
    }
}
