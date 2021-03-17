package com.example.demo.service;

import com.example.demo.api.book.BookClassDTO;
import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.enums.HttpCode;
import com.example.demo.api.service.BookClassService;
import com.example.demo.dao.mapper.book.BookClassMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author superman
 */
@Service
public class BookClassServiceImpl implements BookClassService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BookClassMapper bookClassMapper;

    public BookClassServiceImpl(BookClassMapper bookClassMapper) {
        this.bookClassMapper = bookClassMapper;
    }


    @Override
    public ResultDTO findListByName(String name) {
        logger.info("入参："+name);
        //非空判断
        if(StringUtils.isEmpty(name)){
            return new ResultDTO(HttpCode.FAIL.getCode(), "搜索关键字不能为空");
        }
        //业务查找
        List<BookClassDTO> list = bookClassMapper.findListByName(name);
        logger.info("出参："+list);
        if (CollectionUtils.isEmpty(list)){
            return new ResultDTO(HttpCode.FAIL.getCode(), "暂无分类对应的数据");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"查找成功",list);
    }

    @Override
    public ResultDTO findById(int id) {
        logger.info("入参："+id);
        if (0== id){
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        BookClassDTO byId = bookClassMapper.findById(id);
        logger.info("出参："+byId);
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"查找成功",byId);
    }

    @Override
    public ResultDTO insert(BookClassDTO entity) {
        logger.info("入参："+entity);
        if(StringUtils.isEmpty(entity.getName())){
            return new ResultDTO(HttpCode.FAIL.getCode(), "分类名称不能为空");
        }
        int influence = bookClassMapper.insert(entity);
        if (influence <= 0){
            return new ResultDTO(HttpCode.FAIL.getCode(), "新增失败");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"新增成功");
    }

    @Override
    public ResultDTO update(BookClassDTO entity) {
        logger.info("入参："+entity);
        if(StringUtils.isEmpty(entity.getId())){
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        int influence = bookClassMapper.update(entity);
        if (influence <= 0){
            return new ResultDTO(HttpCode.FAIL.getCode(), "更新失败");
        }
        logger.info("出参："+entity);
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"更新成功");
    }

    @Override
    public ResultDTO delete(int id) {
        logger.info("入参："+id);
        if(0 == id){
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        int influence = bookClassMapper.delete(id);
        if (influence <= 0){
            return new ResultDTO(HttpCode.FAIL.getCode(), "删除失败");
        }
        logger.info("出参："+id);
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"删除成功");
    }
}
