package com.example.demo.dao.mapper.rebcack;

import com.example.demo.api.model.borrow.BookBorrowDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RebackMapper {

    int updateBorrowInfo(BookBorrowDTO bookBorrowDTO);
}
