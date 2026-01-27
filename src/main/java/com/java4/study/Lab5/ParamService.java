package com.java4.study.Lab5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
        @Autowired
        HttpServletRequest request;

        /**
         * Đọc chuỗi giá trị của tham số
         * 
         * @param name         tên tham số
         * @param defaultValue giá trị mặc định
         * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
         */
        public String getString(String name, String defaultValue) {
                String value = request.getParameter(name);
                return value != null ? value : defaultValue;
        }

        /**
         * Đọc số nguyên giá trị của tham số
         * 
         * @param name         tên tham số
         * @param defaultValue giá trị mặc định
         * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
         */
        public int getInt(String name, int defaultValue) {
                String value = request.getParameter(name);
                if (value == null) {
                        return defaultValue;
                }
                try {
                        return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                        return defaultValue;
                }
        }

        /**
         * Đọc số thực giá trị của tham số
         * 
         * @param name         tên tham số
         * @param defaultValue giá trị mặc định
         * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
         */
        public double getDouble(String name, double defaultValue) {
                String value = request.getParameter(name);
                if (value == null) {
                        return defaultValue;
                }
                try {
                        return Double.parseDouble(value);
                } catch (NumberFormatException e) {
                        return defaultValue;
                }
        }

        /**
         * Đọc giá trị boolean của tham số
         * 
         * @param name         tên tham số
         * @param defaultValue giá trị mặc định
         * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
         */
        public boolean getBoolean(String name, boolean defaultValue) {
                String value = request.getParameter(name);
                if (value == null) {
                        return defaultValue;
                }
                return "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value) || "1".equals(value);
        }

        /**
         * Đọc giá trị thời gian của tham số
         * 
         * @param name    tên tham số
         * @param pattern là định dạng thời gian
         * @return giá trị tham số hoặc null nếu không tồn tại
         * @throws RuntimeException lỗi sai định dạng
         */
        public Date getDate(String name, String pattern) {
                String value = request.getParameter(name);
                if (value == null) {
                        return null;
                }
                try {
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        java.util.Date utilDate = sdf.parse(value);
                        return new Date(utilDate.getTime());
                } catch (Exception e) {
                        throw new RuntimeException("Lỗi định dạng ngày tháng: " + value + " với pattern: " + pattern,
                                        e);
                }
        }

        /**
         * Lưu file upload vào thư mục
         * 
         * @param file chứa file upload từ client
         * @param path đường dẫn tính từ webroot
         * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
         * @throws RuntimeException lỗi lưu file
         */
        public File save(MultipartFile file, String path) {
                if (file.isEmpty()) {
                        return null;
                }
                try {
                        // Lấy đường dẫn thực tế của ứng dụng web
                        String uploadPath = request.getServletContext().getRealPath(path);
                        Path uploadDir = Paths.get(uploadPath);

                        // Tạo thư mục nếu chưa tồn tại
                        if (!Files.exists(uploadDir)) {
                                Files.createDirectories(uploadDir);
                        }

                        // Lấy tên file gốc
                        String originalFilename = file.getOriginalFilename();
                        if (originalFilename == null || originalFilename.isEmpty()) {
                                return null;
                        }

                        // Tạo đường dẫn đầy đủ cho file
                        Path filePath = uploadDir.resolve(originalFilename);

                        // Lưu file
                        file.transferTo(filePath.toFile());

                        return filePath.toFile();
                } catch (IOException e) {
                        throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage(), e);
                }
        }
}
