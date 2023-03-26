/**
 * @author Towfiqul Islam
 * @since 3/26/23 11:18 PM
 */

package com.github.laziestcoder.service.impl;

import com.github.laziestcoder.service.CustomerHandlerThreadService;
import com.github.laziestcoder.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Slf4j
@Service
public class CustomerHandlerThreadServiceImpl extends Thread implements CustomerHandlerThreadService {
    private int validWriterCount = 1;
    private int invalidWriterCount = 1;
    private final String FILE_DIR = "export/";
    private final CustomerService customerService;
    private MultipartFile file;

    public CustomerHandlerThreadServiceImpl(CustomerService customerService) {
        this.isFolderCreated();
        this.customerService = customerService;
    }

    @Override
    public void run() {
        log.info("Execution Started Thread [{}]", this.getId());
        try {
            int limit = 100000, sizeOfInvalid = 0, szOfVal = 0;
            Set<String> st = new HashSet<String>();
            Scanner scanner = new Scanner(getFile().getInputStream());
            PrintWriter valWriter = new PrintWriter(getValidFileName());
            PrintWriter invalidWritter = new PrintWriter(getInvalidFileName());
            while (scanner.hasNextLine()) {
                String tmp = scanner.nextLine();
                customerService.process(tmp);
                if (customerService.isValid()) {
                    String temp = customerService.getEmail() + customerService.getPhone();
                    //checking if the data is already in valid set.
                    boolean duplicateFound = st.contains(temp);
                    if (!duplicateFound) {
                        valWriter.println(tmp);
                        //all valid data goes to set
                        st.add(temp);
                        szOfVal++;
                        if (szOfVal == limit) {
                            szOfVal = 0;
                            valWriter.close();
                            validWriterCount++;
                            valWriter = new PrintWriter(getValidFileName());
                        }
                    }
                    //if it is already in set we ignore it.
                } else {
                    invalidWritter.println(tmp);
                    sizeOfInvalid++;
                    if (sizeOfInvalid == limit) {
                        sizeOfInvalid = 0;
                        invalidWritter.close();
                        invalidWriterCount++;
                        invalidWritter = new PrintWriter(getInvalidFileName());
                    }
                }
            }
            scanner.close();
            valWriter.close();
            invalidWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            log.info("Execution Finished Thread [{}]", this.getId());
        }
    }

    private String getValidFileName() {
        String VALID_FILE_NAME = FILE_DIR + "valid";
        return VALID_FILE_NAME + validWriterCount + ".txt";
    }

    private String getInvalidFileName() {
        String INVALID_FILE_NAME = FILE_DIR + "invalid";
        return INVALID_FILE_NAME + invalidWriterCount + ".txt";
    }

    @Override
    public void processThread(MultipartFile file) {
        setFile(file);
        this.start();
    }

    private MultipartFile getFile() {
        return file;
    }

    private void setFile(MultipartFile file) {
        this.file = file;
    }

    private void isFolderCreated() {
        try {
            File file = new File(FILE_DIR);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    log.info("Directory [{}] is created!", FILE_DIR);
                } else {
                    log.error("Directory [{}] is failed to create!", FILE_DIR);
                }
            }
            log.info("Directory exist!");
        } catch (Exception exception) {
            log.error("Exception: [{}]", exception.getMessage(), exception);
        }
    }
}

