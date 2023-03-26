/**
 * @author Towfiqul Islam
 * @since 3/26/23 11:14 PM
 */

package com.github.laziestcoder.service.impl;

import com.github.laziestcoder.service.CustomerHandlerThreadService;
import com.github.laziestcoder.service.ProcessService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProcessServiceImpl implements ProcessService {

    private final CustomerHandlerThreadService customerHandlerThreadService;

    public ProcessServiceImpl(CustomerHandlerThreadService customerHandlerThreadService) {
        this.customerHandlerThreadService = customerHandlerThreadService;
    }

    @Override
    public void processCustomerValidation(MultipartFile file) {
        customerHandlerThreadService.processThread(file);
    }

}