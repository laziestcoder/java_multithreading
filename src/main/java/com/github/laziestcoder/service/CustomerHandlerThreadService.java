/**
 * @author Towfiqul Islam
 * @since 3/26/23 11:18 PM
 */

package com.github.laziestcoder.service;

import org.springframework.web.multipart.MultipartFile;

public interface CustomerHandlerThreadService {
    void processThread(MultipartFile file);
}

