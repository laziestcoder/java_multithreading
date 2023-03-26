/**
 * @author Towfiqul Islam
 * @since 3/25/23 11:05 PM
 */

package com.github.laziestcoder.controller;

import com.github.laziestcoder.dto.RestResponse;
import com.github.laziestcoder.service.ProcessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private final ProcessService processService;

    public CustomerRestController(ProcessService processService) {
        this.processService = processService;
    }

    @ResponseBody
    @RequestMapping(
            value = "/import/customer-info",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public RestResponse importCustomerInformationFile(
            @RequestPart(value = "customerFile", required = true) MultipartFile customerFile) {
        Instant startTime = Instant.now();
        System.out.println("Found: " + customerFile.getOriginalFilename());
        processService.processCustomerValidation(customerFile);
        Instant endTime = Instant.now();
        long elapsedTimeInNS = Duration.between(startTime, endTime).toNanos();
        long elapsedTimeInMS = Duration.between(startTime, endTime).toMillis();
        String unit = elapsedTimeInMS > 0 ? "ms" : "ns";
        long value = elapsedTimeInMS > 0 ? elapsedTimeInMS : elapsedTimeInNS;
        String message = MessageFormat.format("Runtime: {0} {1}", value, unit);
        System.out.println(message);
        return RestResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message(message)
                .data(null)
                .build();
    }
}
