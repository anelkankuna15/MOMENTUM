package momentum.investment.momemtuminvestment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import momentum.investment.momemtuminvestment.model.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}

