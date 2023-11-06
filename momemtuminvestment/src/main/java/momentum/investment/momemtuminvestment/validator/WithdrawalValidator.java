package momentum.investment.momemtuminvestment.validator;

import momentum.investment.momemtuminvestment.model.Product;
import momentum.investment.momemtuminvestment.model.Withdrawal;
import momentum.investment.momemtuminvestment.event.ProductType;

import java.math.BigDecimal;

public class WithdrawalValidator {

    public static boolean isInvestorEligibleForRetirementWithdrawal(Withdrawal withdrawal) {
        Product product = withdrawal.getProduct();
        return product.getType() != ProductType.RETIREMENT || withdrawal.getInvestor().getAge() > 65;
    }

    public static boolean isWithdrawalAmountValid(Withdrawal withdrawal) {
        BigDecimal withdrawalAmount = withdrawal.getAmount();
        BigDecimal currentBalance = withdrawal.getProduct().getBalance();
        BigDecimal maxWithdrawalAmount = currentBalance.multiply(new BigDecimal("0.9"));

        return withdrawalAmount.compareTo(currentBalance) <= 0 && withdrawalAmount.compareTo(maxWithdrawalAmount) <= 0;
    }
}
