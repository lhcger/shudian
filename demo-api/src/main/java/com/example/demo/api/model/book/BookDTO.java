package com.example.demo.api.model.book;

import com.example.demo.api.model.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**书籍
 * @author superman
 * **/

@EqualsAndHashCode(callSuper = true)
@Data
public class BookDTO extends BaseDTO{
    /**
     * 书籍名称
     **/
    private String bookName;


    /**
     * 书籍分类名称
     */
    private int bookClassId;


    /**
     * 价格
     */
    private BigDecimal bookPrice;

    /**
     * 书籍数量
     */
    private int bookCount;

    /**
     * 出版社
     */
    private String bookPublish;

    /**
     * 作者
     */
    private String bookAuthor;

    /**
     * 封面
     */
    private String bookImg;

    /**
     * 出版日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate;


}
