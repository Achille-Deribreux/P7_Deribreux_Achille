package com.nnk.springboot;

import com.nnk.springboot.domain.CustomErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
public class CustomErrorResponseTest {

    @Test
    void Test() {
        //Given
        String message = "this is an error mesage";
        Throwable throwable = null;
        HttpStatus httpStatus = null;
        ZonedDateTime timestamp =  ZonedDateTime.now();
        //When
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(message,throwable,httpStatus,timestamp);
        //Then
        assertEquals(message, customErrorResponse.getMessage());
        assertNull(customErrorResponse.getHttpStatus());
        assertEquals(timestamp, customErrorResponse.getTimestamp());
        assertNull(customErrorResponse.getThrowable());
    }
}
