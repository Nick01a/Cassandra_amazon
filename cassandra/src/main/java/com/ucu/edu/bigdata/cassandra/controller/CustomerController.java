package com.ucu.edu.bigdata.cassandra.controller;

import com.ucu.edu.bigdata.cassandra.dto.CustomerDto;
import com.ucu.edu.bigdata.cassandra.dto.request.DateRequestDto;
import com.ucu.edu.bigdata.cassandra.service.CustomerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/customers")
@CrossOrigin("*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerController {

    private static final String LOG_MESSAGE = "Endpoint - {}() call";

    final CustomerService customerService;

    @PostMapping("/productive")
    public ResponseEntity<List<CustomerDto>> getProductiveCustomers(@RequestBody DateRequestDto dateRequestDto) {
        log.debug(LOG_MESSAGE, "getProductiveCustomers");
        return new ResponseEntity<>(customerService.getProductiveCustomers(dateRequestDto), HttpStatus.OK);
    }

    @PostMapping("/haters")
    public ResponseEntity<List<CustomerDto>> getHaters(@RequestBody DateRequestDto dateRequestDto) {
        log.debug(LOG_MESSAGE, "getHaters");
        return new ResponseEntity<>(customerService.getHaters(dateRequestDto), HttpStatus.OK);
    }

    @PostMapping("/backers")
    public ResponseEntity<List<CustomerDto>> getBackers(@RequestBody DateRequestDto dateRequestDto) {
        log.debug(LOG_MESSAGE, "getBackers");
        return new ResponseEntity<>(customerService.getBackers(dateRequestDto), HttpStatus.OK);
    }
}
