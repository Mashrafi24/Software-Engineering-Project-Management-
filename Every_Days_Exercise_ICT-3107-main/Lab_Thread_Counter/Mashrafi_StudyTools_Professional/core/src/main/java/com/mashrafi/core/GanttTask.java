package com.mashrafi.core;
import java.time.LocalDate;
public record GanttTask(long id,int dayNumber,LocalDate taskDate,String title,String description,int progress,String status) {}