package com.example.bookstore.providers;

import org.springframework.stereotype.Service;

@Service
public class CacheService {
    private String page;
    public void setPage(String page) {
        this.page = page;
    }
    public String getPage() {
        return page;
    }
}
