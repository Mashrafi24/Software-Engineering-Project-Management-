package com.mashrafi.core;
import java.time.LocalDateTime;
public record Calculation(long id,String expression,String result,LocalDateTime createdAt) {}