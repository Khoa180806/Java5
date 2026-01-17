package com.java4.study.Lab4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Staff {
    private String id;
    private String fullname;
    @Builder.Default
    private String photo = "default-avatar.png";
    @Builder.Default
    private Boolean gender = false;
    @Builder.Default
    @DateTimeFormat(
            pattern = "MM/dd/yyyy")
    private Date birthday = null; // Consider requiring this field to be set explicitly
    @Builder.Default
    private double salary =
            12345.6789;
    @Builder.Default
    private Integer level = 0;
}