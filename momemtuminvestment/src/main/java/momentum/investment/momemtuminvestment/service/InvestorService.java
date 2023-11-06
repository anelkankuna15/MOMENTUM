package momentum.investment.momemtuminvestment.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import momentum.investment.momemtuminvestment.model.Investor;
import momentum.investment.momemtuminvestment.repository.InvestorRepository;

@Service
public class InvestorService {
    @Autowired
    private InvestorRepository investorRepository;

    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    public Investor getInvestorById(Long id) {
        return investorRepository.findById(id).orElse(null);
    }

    public Investor createInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    public Investor updateInvestor(Investor updatedInvestor) {
        return investorRepository.save(updatedInvestor);
    }

    public void deleteInvestor(Long id) {
        investorRepository.deleteById(id);
    }

    public int getInvestorAge(Long investorId) {

        Investor investor = investorRepository.findById(investorId).orElse(null);

        if (investor != null && investor.getDateOfBirth() != null) {
            // Calculate the age based on the date of birth and the current date
            LocalDate birthDate = investor.getDateOfBirth();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
        }

        return 0;
    }
}
