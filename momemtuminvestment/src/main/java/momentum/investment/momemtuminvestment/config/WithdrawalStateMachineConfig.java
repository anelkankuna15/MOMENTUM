package momentum.investment.momemtuminvestment.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;

import momentum.investment.momemtuminvestment.event.WithdrawalProcessEvent;
import momentum.investment.momemtuminvestment.state.WithdrawalProcessState;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;


@Configuration
@EnableStateMachine
public class WithdrawalStateMachineConfig extends StateMachineConfigurerAdapter<WithdrawalProcessState, WithdrawalProcessEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<WithdrawalProcessState, WithdrawalProcessEvent> states)
            throws Exception {
        states
            .withStates()
            .initial(WithdrawalProcessState.STARTED)
            .states(EnumSet.allOf(WithdrawalProcessState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<WithdrawalProcessState, WithdrawalProcessEvent> transitions)
            throws Exception {
        transitions
            .withExternal()
            .source(WithdrawalProcessState.STARTED).target(WithdrawalProcessState.EXECUTING)
            .event(WithdrawalProcessEvent.EXECUTE)
            .and()
            .withExternal()
            .source(WithdrawalProcessState.EXECUTING).target(WithdrawalProcessState.DONE)
            .event(WithdrawalProcessEvent.COMPLETE);
    }
}

