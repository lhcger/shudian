package com.example.demo.api.model.borrow;

import com.example.demo.api.model.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/*
* 借书实体类
* 1.编写DAO层实体
* 2.编写
*
* */
@Data
public class BookBorrowDTO extends BaseDTO {

    /*
    * 用户ID
    * */
    private int userId;

    /*
    * 用户名
    * */
    private String userName;

    /*
    * 书籍ID
    * */
    private int bookId;

    /*
    * 书籍名称
    * */
    private String bookName;

    /*
    * 借书数量
    * */
    private int count;

    /*
    * 借书时间
    * */
    private Date startDate;

    /*
    * 还书时间
    * */
    private Date endDate;

    /*
    * 借书费用
    * */
    private BigDecimal price;

    /*
    * 借书实际费用
    * */
    private BigDecimal tradeFee;
}
