package com.example.blogproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Component
public class ResponseDto<T> {
   //  HttpStatus status;  // 200,404 , OK
     int status;
     T data;
}
