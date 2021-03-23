package com.example.demo.service.reback;

import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.enums.HttpCode;
import com.example.demo.api.enums.ValidFlagEnum;
import com.example.demo.api.model.book.BookDTO;
import com.example.demo.api.model.borrow.BookBorrowDTO;
import com.example.demo.api.service.reback.RebackService;
import com.example.demo.dao.mapper.book.BookMapper;
import com.example.demo.dao.mapper.borrow.BookBorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;


@Service
public class RebackServiceImpl implements RebackService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookBorrowMapper bookBorrowMapper;

    @Override
    public ResultDTO updateBorrowInfo(BookBorrowDTO bookBorrowDTO) {
        int bookId = bookBorrowDTO.getBookId();
        if (0 >= bookId) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "书籍ID不能为空");
        }
        if (0 >= bookBorrowDTO.getId()) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据主键不能为空");
        }
        // 1-查找本地是否存在这个书籍
        BookDTO bookDTO = bookMapper.findById(bookId);
        if (null == bookDTO) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "书籍信息不存在");
        }

        // 2-本地借书信息查询
        BookBorrowDTO localBookBorrowDTO = bookBorrowMapper.findById(bookBorrowDTO.getId());
        if (null == localBookBorrowDTO) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "借书信息不存在，请确保信息合法或联系系统管理员");
        }
        // 2.1-本地借书的数量
        int borrowCount = localBookBorrowDTO.getCount();
        int temp1Count = 0;
        if (!StringUtils.isEmpty(localBookBorrowDTO.getTemp1())) {
            temp1Count = Integer.parseInt(localBookBorrowDTO.getTemp1());
        }
        if (temp1Count == borrowCount) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "您已还完全部书籍，无需归还");
        }
        // 2.2-还书的数量
        int rebackCount = bookBorrowDTO.getCount();
        // 当归还数量不相等时，当前归还数量保存在temp1字段
        return doReback(rebackCount, borrowCount - temp1Count, localBookBorrowDTO, bookDTO);
    }

    /**
     * 真正的还书操作
     *
     * @param rebackCount        还书数量
     * @param borrowCount        借书数量
     * @param localBookBorrowDTO 本地借书实体类
     * @param bookDTO            本地书籍实体类
     * @return
     */
    private ResultDTO doReback(int rebackCount, int borrowCount, BookBorrowDTO localBookBorrowDTO, BookDTO bookDTO) {
        int nowTempCount = 0;
        // 3-还书判断
        if (rebackCount > borrowCount) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "归还数量大于借阅数量，请确保归还数量正确");
        } else if (rebackCount == borrowCount) {
            // 借书还书一样，数据DISABLE，book表数量增加
            // 1.操作还书信息表
            localBookBorrowDTO.setCount(0);
            // 本地上一次归还的数量
            if (!StringUtils.isEmpty(localBookBorrowDTO.getTemp1())) {
                nowTempCount = Integer.parseInt(localBookBorrowDTO.getTemp1());
            }
            localBookBorrowDTO.setTemp1(String.valueOf(rebackCount + nowTempCount));
            localBookBorrowDTO.setTemp2(String.valueOf(new Date()));
            localBookBorrowDTO.setValidFlag(ValidFlagEnum.DISABLE);
            bookBorrowMapper.update(localBookBorrowDTO);
            // 2.追加book信息表剩余数量
            bookDTO.setBookCount(bookDTO.getBookCount() + rebackCount);
            bookDTO.setUpdateDate(new Date());
            bookMapper.update(bookDTO);
        } else {
            // 1.还书数量小于借书数量，更新还书数量(temp1)，同时更新book剩余数量
            if (!StringUtils.isEmpty(localBookBorrowDTO.getTemp1())) {
                nowTempCount = Integer.parseInt(localBookBorrowDTO.getTemp1());
            }
            localBookBorrowDTO.setTemp1(String.valueOf(rebackCount + nowTempCount));
            localBookBorrowDTO.setTemp2(String.valueOf(new Date()));
            bookBorrowMapper.update(localBookBorrowDTO);
            // 2.追加book信息表剩余数量
            bookDTO.setBookCount(bookDTO.getBookCount() + rebackCount);
            bookDTO.setUpdateDate(new Date());
            bookMapper.update(bookDTO);
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(), "归还成功,还有" + (borrowCount - rebackCount) + "本书尚未归还,请在：" + localBookBorrowDTO.getEndDate() + "之前归还剩余书籍");
    }
}