/**
 * @author Towfiqul Islam
 * @since 3/26/23 11:14 PM
 */

package com.github.laziestcoder.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProcessService {
    void processCustomerValidation(MultipartFile file);
}
