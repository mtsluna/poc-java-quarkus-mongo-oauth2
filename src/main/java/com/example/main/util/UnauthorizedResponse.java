package com.example.main.util;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UnauthorizedResponse {

    private String status;
    private String message;

}
