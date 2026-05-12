package ch.samt.springtransaction;

import ch.samt.springtransaction.data.AccountRepository;
import ch.samt.springtransaction.model.Account;
import ch.samt.springtransaction.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();

        account1 = new Account();
        account1.setOwner("Anna");
        account1.setBalance(new BigDecimal("1000"));
        account1 = accountRepository.save(account1);

        account2 = new Account();
        account2.setOwner("Gino");
        account2.setBalance(BigDecimal.ZERO);
        account2 = accountRepository.save(account2);
    }

    @Test
    void transferShouldSucceedWhenNoError() {
        accountService.transfer(account1.getId(), account2.getId(),
                new BigDecimal("600"), false);

        Account result1 = accountRepository.findById(account1.getId()).orElseThrow();
        Account result2 = accountRepository.findById(account2.getId()).orElseThrow();

        assertEquals(0, new BigDecimal("400").compareTo(result1.getBalance()),
                "Il conto 1 dovrebbe avere saldo 400");
        assertEquals(0, new BigDecimal("600").compareTo(result2.getBalance()),
                "Il conto 2 dovrebbe avere saldo 600");
    }

    @Test
    void transferShouldRollbackOnError() {
        BigDecimal initialBalance1 = account1.getBalance();
        BigDecimal initialBalance2 = account2.getBalance();

        assertThrows(RuntimeException.class, () ->
                accountService.transfer(account1.getId(), account2.getId(),
                        new BigDecimal("600"), true));

        Account result1 = accountRepository.findById(account1.getId()).orElseThrow();
        Account result2 = accountRepository.findById(account2.getId()).orElseThrow();

        assertEquals(0, initialBalance1.compareTo(result1.getBalance()),
                "Rollback: il conto 1 deve tornare al saldo iniziale di 1000");
        assertEquals(0, initialBalance2.compareTo(result2.getBalance()),
                "Rollback: il conto 2 deve tornare al saldo iniziale di 0");
    }
}
