package com.example.demo.service.book;

import com.example.demo.api.model.book.BookDTO;
import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.enums.HttpCode;
import com.example.demo.api.service.book.BookService;
import com.example.demo.dao.mapper.book.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //注入dao层
    @Autowired
    private  BookMapper bookMapper;


    @Override
    public ResultDTO findListByName(String name) {
        logger.info("入参：" + name);
        // 非空判断
        if(StringUtils.isEmpty(name)) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "搜索关键字不能为空");
        }
        // 业务查找
        List<BookDTO> list = bookMapper.findListByName(name);
        logger.info("出参：" + list);
        if(CollectionUtils.isEmpty(list)) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "暂无对应书籍数据");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(), "查找成功", list);
    }

    @Override
    public ResultDTO findById(int id) {
        logger.info("入参：" + id);
        // 非空判断
        if(0 == id) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        // 业务查找
        BookDTO bookClassDTO = bookMapper.findById(id);
        if(null == bookClassDTO) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "暂无书籍数据");
        }
        logger.info("出参：" + bookClassDTO);
        return new ResultDTO(HttpCode.SUCCESS.getCode(), "查找成功", bookClassDTO);
    }

    @Override
    public ResultDTO insert(BookDTO entity) {
        logger.info("入参：" + entity);
        // 非空判断
        if(StringUtils.isEmpty(entity.getBookName())) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "书籍名称不能为空");
        }
        if(StringUtils.isEmpty(entity.getBookClassId())) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "书籍分类编号不能为空");
        }
        if(0 == entity.getBookCount()) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "书籍数量不能小于0");
        }
        int influenceNumber = bookMapper.insert(entity);
        if(influenceNumber <= 0) {
            // 新增失败
            return new ResultDTO(HttpCode.FAIL.getCode(), "新增失败");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(), "新增成功");
    }

    @Override
    public ResultDTO update(BookDTO entity) {
        logger.info("入参：" + entity);
        // 非空判断
        if(StringUtils.isEmpty(entity.getId())) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        int influenceNumber = bookMapper.update(entity);
        if(influenceNumber <= 0) {
            // 更新失败
            return new ResultDTO(HttpCode.FAIL.getCode(), "更新失败");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(), "更新成功");
    }

    @Override
    public ResultDTO delete(int id) {
        logger.info("入参：" + id);
        // 非空判断
        if(0 == id) {
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        int influenceNumber = bookMapper.delete(id);
        if(influenceNumber <= 0) {
            // 删除失败
            return new ResultDTO(HttpCode.FAIL.getCode(), "删除失败");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(), "删除成功");
    }
}