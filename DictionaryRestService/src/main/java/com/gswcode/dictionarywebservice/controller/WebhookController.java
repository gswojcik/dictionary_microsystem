package com.gswcode.dictionarywebservice.controller;

import com.gswcode.dictionarywebservice.dto.ErrorResponse;
import com.gswcode.dictionarywebservice.fileprocessor.FileUpdateService;
import com.gswcode.dictionarywebservice.dto.WebhookRequest;
import com.gswcode.dictionarywebservice.dto.WebhookResponse;
import com.gswcode.dictionarywebservice.fileprocessor.TerminationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("update")
public class WebhookController {

    private final FileUpdateService service;

    public WebhookController(FileUpdateService service) {
        this.service = service;
    }

    @PostMapping("/setWebhook")
    public ResponseEntity<Object> add(@RequestBody WebhookRequest request) {
        if (!request.getWebhookUrl().contains("http")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST, "Incorect webhook url, it must start with *http*"));
        }
        if (service.isProcessing()) {
            return ResponseEntity
                    .status(HttpStatus.LOCKED)
                    .body(new ErrorResponse(HttpStatus.LOCKED, "The process is running, it is not allowed to call multiple times"));
        }
        long filesQuantity = service.runUpdate(request.getWebhookUrl());
        return ResponseEntity.ok(new WebhookResponse(filesQuantity));
    }

    @GetMapping("/terminate")
    public ResponseEntity<Object> terminateUpdating() {
        TerminationStatus status = service.stopProcessing();
        if (status.getTerminatedFileName().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.LOCKED)
                    .body(new ErrorResponse(HttpStatus.LOCKED, "At this moment there is no updating"));
        }
        return ResponseEntity.ok(status);
    }

}
