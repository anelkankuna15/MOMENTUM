package momentum.investment.momemtuminvestment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import momentum.investment.momemtuminvestment.model.Withdrawal;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}

