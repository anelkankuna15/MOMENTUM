package momentum.investment.momemtuminvestment.model;

import jakarta.persistence.*;
import momentum.investment.momemtuminvestment.event.WithdrawalStatus;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal withdrawalAmount;
    private WithdrawalStatus status;

    @OneToMany(mappedBy = "withdrawal")
    private List<AuditRecord> auditRecords;

    public Withdrawal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public WithdrawalStatus getStatus() {
        return status;
    }

    public void setStatus(WithdrawalStatus status) {
        this.status = status;
    }

    public List<AuditRecord> getAuditRecords() {
        return auditRecords;
    }

    public void setAuditRecords(List<AuditRecord> auditRecords) {
        this.auditRecords = auditRecords;
    }

    public int getInvestorAge() {
        if (investor != null) {
            return investor.getAge();
        }
        return 0;
    }
    
    public Long getInvestorId() {
        return investor.getId();
    }

    public Long getProductId() {
        return product.getId();
    }

    public BigDecimal getAmount() {
        return null;
    }
}
