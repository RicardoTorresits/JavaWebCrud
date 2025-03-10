/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

/**
 *
 * @author ricardotorres
 */
public class Result<T> {
    
    
    private final T data;
    private final boolean success;
    private final String message;
    
    public Result(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(null, false, message);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(data, true, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    
}
