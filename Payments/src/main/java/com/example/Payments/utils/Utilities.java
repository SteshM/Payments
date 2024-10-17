package com.example.Payments.utils;

import com.example.Payments.dto.ResponseDTO.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class Utilities {
    public ResponseDTO successResponse(String statusMessage, Object result) {
        var response = new ResponseDTO();
        response.setStatusCode("00");
        response.setStatusMessage(statusMessage);
        response.setResult(result);
        return response;
    }

    public ResponseDTO failedResponse(int statusCode, String statusMessage, Object result) {
        var response = new ResponseDTO();
        response.setStatusCode("01");
        response.setStatusMessage(statusMessage);
        response.setResult(result);

        return response;
    }
}
