package ch.samt.springtransaction.service;

import ch.samt.springtransaction.data.AccountRepository;
import ch.samt.springtransaction.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Trasferisce una somma dal conto A al conto B.
     * Simula un errore tra l'addebito e l'accredito per testare il rollback.
     * Se {@code simulateError} è true, viene lanciata una RuntimeException
     * dopo l'addebito e prima dell'accredito.
     * <p>
     * Con @Transactional: l'errore causa il rollback completo.
     * Senza @Transactional: l'addebito persiste ma l'accredito no (inconsistenza).
     */
    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount,
                         boolean simulateError) {
        Account from = accountRepository.findById(fromAccountId).orElseThrow();
        Account to = accountRepository.findById(toAccountId).orElseThrow();

        from.setBalance(from.getBalance().subtract(amount));
        accountRepository.save(from);

        if (simulateError) {
            throw new RuntimeException("Errore simulato durante il trasferimento");
        }

        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(to);
    }
}
