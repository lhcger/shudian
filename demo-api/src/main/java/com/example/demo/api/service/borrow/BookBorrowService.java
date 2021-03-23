package com.example.demo.api.service.borrow;

import com.example.demo.api.constants.ResultDTO;

import java.util.Date;

public interface BookBorrowService {

    ResultDTO doBorrow(int bookId, Date startDate, Date endDate, int borrowCount, int userId, String userName, boolean vipFlag);
}
