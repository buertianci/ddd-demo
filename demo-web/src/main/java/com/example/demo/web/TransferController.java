package com.example.demo.web;

import com.example.demo.application.TransferService;
import com.example.demo.common.Result;
import com.example.demo.exception.DailyLimitExceededException;
import com.example.demo.request.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/api/users/transfer")
    Result<Boolean> transfer(@RequestBody TransferRequest request) {
        Result<Boolean> result = null;
        try {
            result = transferService.transfer(request.getSourceUserId(), request.getTargetAccountNumber(), request.getTargetAmount(), request.getTargetCurrency());
        } catch (Exception | DailyLimitExceededException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
