package com.kaansenada.accounts.service;

import com.kaansenada.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * Creates a new account for the given customer
     *
     * @param customerDto customer details
     */
    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccountDetails(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

}
