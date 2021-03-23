package com.example.demo.api.service.reback;

import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.model.borrow.BookBorrowDTO;

public interface RebackService {

    ResultDTO updateBorrowInfo(BookBorrowDTO bookBorrowDTO);
}
