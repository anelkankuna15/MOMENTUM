package momentum.investment.momemtuminvestment.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import momentum.investment.momemtuminvestment.event.ProductType;
import momentum.investment.momemtuminvestment.model.Investor;
import momentum.investment.momemtuminvestment.model.Product;
import momentum.investment.momemtuminvestment.model.Withdrawal;
import momentum.investment.momemtuminvestment.repository.WithdrawalRepository;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private InvestorService investorService;

    public List<Withdrawal> getAllWithdrawals() {
        return withdrawalRepository.findAll();
    }

    public Withdrawal getWithdrawalById(Long id) {
        return withdrawalRepository.findById(id).orElse(null);
    }

    public Withdrawal createWithdrawal(Withdrawal withdrawal) {
        Product product = productService.getProductById(withdrawal.getProductId());

        if (product == null || withdrawal.getWithdrawalAmount() == null || withdrawal.getWithdrawalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }

        if (product.getType() == ProductType.RETIREMENT) {
            Investor investor = investorService.getInvestorById(withdrawal.getInvestorId());
            int investorAge = investor.calculateInvestorAge();

            if (investorAge <= 65) {
                return null;
            }
        }

        if (withdrawal.getWithdrawalAmount().compareTo(product.getBalance()) > 0) {
            return null;
        }

        BigDecimal maxWithdrawalAmount = product.getBalance().multiply(new BigDecimal("0.9"));
        if (withdrawal.getWithdrawalAmount().compareTo(maxWithdrawalAmount) > 0) {
            return null;
        }

        Withdrawal createdWithdrawal = withdrawalRepository.save(withdrawal);
        return createdWithdrawal;
    }

    public Withdrawal updateWithdrawal(Withdrawal updatedWithdrawal) {
        return withdrawalRepository.save(updatedWithdrawal);
    }

    public void deleteWithdrawal(Long id) {
        withdrawalRepository.deleteById(id);
    }
}
