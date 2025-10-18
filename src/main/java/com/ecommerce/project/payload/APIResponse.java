package com.ecommerce.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mohamed Ghanem
 * @created 27-09-2025
 * @description Service class for UserService
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse {

    public String message;
    public boolean status;
}