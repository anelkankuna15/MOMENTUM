package momentum.investment.momemtuminvestment.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WithdrawalProcessService {
    @Autowired
    private WithdrawalProcessRepository withdrawalProcessRepository;

    // Implement methods to create, transition, and audit the withdrawal process

    public WithdrawalProcess createWithdrawalProcess(Long withdrawalId, WithdrawalProcessState initialState) {
        WithdrawalProcess withdrawalProcess = new WithdrawalProcess();
        withdrawalProcess.setWithdrawalId(withdrawalId);
        withdrawalProcess.setState(initialState);
        withdrawalProcess.setTimestamp(LocalDateTime.now());
        return withdrawalProcessRepository.save(withdrawalProcess);
    }

    public WithdrawalProcess transitionWithdrawalProcessState(Long withdrawalProcessId, WithdrawalProcessState newState) {
        WithdrawalProcess withdrawalProcess = withdrawalProcessRepository.findById(withdrawalProcessId).orElse(null);
        if (withdrawalProcess != null) {
            String previousState = withdrawalProcess.getState().toString();
            withdrawalProcess.setState(newState);
            withdrawalProcess.setTimestamp(LocalDateTime.now());
            withdrawalProcess.setPreviousState(previousState);
            return withdrawalProcessRepository.save(withdrawalProcess);
        }
        return null; 
    }


    public List<WithdrawalProcess> getAllWithdrawalProcesses() {
        return withdrawalProcessRepository.findAll();
    }

    public WithdrawalProcess getWithdrawalProcessById(Long withdrawalProcessId) {
        return withdrawalProcessRepository.findById(withdrawalProcessId).orElse(null);
    }


    public WithdrawalProcess auditWithdrawalProcess(WithdrawalProcess withdrawalProcess, String auditDetails) {
        return withdrawalProcessRepository.save(withdrawalProcess);
    }

    public WithdrawalProcess updateWithdrawalProcess(WithdrawalProcess withdrawalProcess) {
        return null;
    }

    public WithdrawalProcess createWithdrawalProcess(WithdrawalProcess withdrawalProcess) {
        return null;
    }
}
