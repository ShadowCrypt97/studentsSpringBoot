package com.example.projectSrpingboot.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class GeneralResponse<T> {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    private boolean status;
    private String message;
    private T body;

    public ResponseEntity<GeneralResponse<T>> createResponse() {
        return new ResponseEntity<>(this, HttpStatus.OK);
    }

    public ResponseEntity<GeneralResponse<T>> createResponse(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}
