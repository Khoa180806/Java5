package com.java4.study.Lab4;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
    @NotBlank(message = "Chưa nhập email")
    @Email(message = "Email không đúng định dạng")
    private String id;
    @NotBlank(message = "Chưa nhập họ và tên")
    private String fullname;
    @Builder.Default
    private String photo = "default-avatar.png";
    @Builder.Default
    @NotNull(message = "Chưa chọn giới tính")
    private Boolean gender = false;
    @Builder.Default
    @NotNull(message = "Chưa nhập ngày sinh")
    @Past(message = "Ngày sinh không hợp lệ")
    @DateTimeFormat(
            pattern = "MM/dd/yyyy")
    private Date birthday = null; // Consider requiring this field to be set explicitly
    @Builder.Default
    @Min(value = 1000, message = "Lương tối thiểu phải là 1000")
    @NotNull(message = "Chưa nhập lương")
    private double salary = 1000;
    @Builder.Default
    private Integer level = 0;
}