package com.ecommerce.product_service.api.hateaoas;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PageApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int totalPages;
    private int currentPage;
    private boolean hasNextPage;

}
