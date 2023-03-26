/**
 * @author Towfiqul Islam
 * @since 3/26/23 11:14 PM
 */

package com.github.laziestcoder.service.impl;

import com.github.laziestcoder.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private String email;
    private String phone;
    private boolean isValid = true;

    @Override
    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        // Remove any non-digit characters from the phone number string
        String cleanedNumber = phoneNumber.replaceAll("\\D", "");
        char firstDigit = cleanedNumber.charAt(0);

        //removing country code
        if (firstDigit == '1') {
            cleanedNumber = cleanedNumber.substring(1);
        }

        // All checks if passed the phone number is valid
        return cleanedNumber.length() == 10;
    }

    private boolean validateEmail(String email) {

        // Check that the email has exactly one @ character
        int atSymbolCount = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                atSymbolCount++;
            }
        }
        if (atSymbolCount != 1) {
            return false;
        }

        // Check that the email does not start or end with a period
        if (email.charAt(0) == '.' || email.charAt(email.length() - 1) == '.') {
            return false;
        }

        // Check that the email contains only valid characters
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.@";
        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (validChars.indexOf(c) == -1) {
                return false;
            }
        }

        // Check that the email contains at least one period and one non-period character after the @ symbol
        int atSymbolIndex = email.indexOf('@');
        String afterAtSymbol = email.substring(atSymbolIndex + 1);

        // All checks passed, the email is valid
        return afterAtSymbol.indexOf('.') != -1 && afterAtSymbol.indexOf('.') != afterAtSymbol.length() - 1;

    }

    @Override
    public void process(String customer) {
        //indicates the columns of the data
        //col-1 : firstName, col-2:lastName ...
        int columnNo = 1;
        //tmp will split the strings by ,
        String tmp = "";
        for (int i = 0; i < customer.length(); i++) {
            char ch = customer.charAt(i);
            //if comma is found then it means we are at the end of some data
            if (ch == ',') {
                //if columnNo = 1, it is first name
                if (columnNo == 1) {
                }
                //if columnNo = 2, it is city

                if (columnNo == 2) {
                }
                //if columnNo = 3, it is last name
                if (columnNo == 3) {
                }
                //if columnNo = 4, it is state code
                if (columnNo == 4) {
                }
                //if columnNo = 5, it is post code
                if (columnNo == 5) {
                }
                //if columnNo = 6, it is phone no
                if (columnNo == 6) {
                    setPhone(tmp);
                    if (!validatePhoneNumber((tmp))) {
                        isValid = false;
                        break;
                    }
                }
                //if columnNo = 7, it is mail
                if (columnNo == 7) {
                    setEmail(tmp);
                    if (!validateEmail(tmp)) {
                        isValid = false;
                        break;
                    }
                }
                //if columNo = 8, it is ip
                if (columnNo == 8) {
                }

                //resetting the tmp
                tmp = "";
                //pointing to the next column
                columnNo++;
            } else {
                tmp += ch;
            }
        }

    }

}