package momentum.investment.momemtuminvestment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import momentum.investment.momemtuminvestment.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query method to find products by investor ID
    @Query("SELECT p FROM Product p WHERE p.investor.id = :investorId")
    List<Product> findByInvestorId(@Param("investorId") Long investorId);
}
