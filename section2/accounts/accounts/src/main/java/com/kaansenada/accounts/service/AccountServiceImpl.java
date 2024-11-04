package com.kaansenada.accounts.service;

import com.kaansenada.accounts.dto.CustomerDto;
import com.kaansenada.accounts.entity.Account;
import com.kaansenada.accounts.entity.Customer;
import com.kaansenada.accounts.enums.AccountEnum;
import com.kaansenada.accounts.exception.CustomerAlreadyExistsException;
import com.kaansenada.accounts.mapper.CustomerMapper;
import com.kaansenada.accounts.repository.AccountRepository;
import com.kaansenada.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     * Creates a new account for the given customer
     *
     * @param customerDto customer details
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number " + customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long randomAccNumber = (long) (Math.random() * 1000000000);
        account.setAccountNumber(randomAccNumber);
        account.setAccountType(AccountEnum.SAVINGS.toString());
        account.setBranchAddress(AccountEnum.ADDRESS.toString());
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        return account;
    }
}
