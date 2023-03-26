/**
 * @author Towfiqul Islam
 * @since 3/26/23 11:14 PM
 */

package com.github.laziestcoder.service;

public interface CustomerService {
    void process(String customer);
    boolean isValid();
    String getEmail();
    String getPhone();
}
