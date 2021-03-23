package com.example.demo.web.reback;

import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.enums.HttpCode;
import com.example.demo.api.model.borrow.BookBorrowDTO;
import com.example.demo.api.service.reback.RebackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reback")
public class RebackController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RebackService rebackService;

    @RequestMapping("/doBack")
    public ResultDTO rebackBook(@RequestBody BookBorrowDTO bookBorrowDTO) {
        try {
            return rebackService.updateBorrowInfo(bookBorrowDTO);
        } catch (Exception e) {
            logger.error("系统异常：" + e);
            return new ResultDTO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }
}