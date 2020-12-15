package com.example.demo.service;

import com.example.demo.api.book.BookClassDTO;
import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.enums.HttpCode;
import com.example.demo.api.service.BookClassService;
import com.example.demo.dao.mapper.book.BookClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class BookClassServiceImpl implements BookClassService {
    @Autowired
    private BookClassMapper bookClassMapper;


    @Override
    public ResultDTO findListByName(String name) {
        //非空判断
        if(StringUtils.isEmpty(name)){
            return new ResultDTO(HttpCode.FAIL.getCode(), "搜索关键字不能为空");
        }
        //业务查找
        List<BookClassDTO> list = bookClassMapper.findListByName(name);
        if (CollectionUtils.isEmpty(list)){
            return new ResultDTO(HttpCode.FAIL.getCode(), "暂无分类对应的数据");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"查找成功",list);
    }

    @Override
    public ResultDTO findById(int id) {
        if (0 == id){
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        BookClassDTO byId = bookClassMapper.findById(id);
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"查找成功",byId);
    }

    @Override
    public ResultDTO insert(BookClassDTO entity) {
        if(StringUtils.isEmpty(entity.getName())){
            return new ResultDTO(HttpCode.FAIL.getCode(), "分类名称不能为空");
        }
        int insert = bookClassMapper.insert(entity);
        if (insert <= 0){
            return new ResultDTO(HttpCode.FAIL.getCode(), "新增失败");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"新增成功");
    }

    @Override
    public ResultDTO update(BookClassDTO entity) {
        if(StringUtils.isEmpty(entity.getId())){
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        int insert = bookClassMapper.update(entity);
        if (insert <= 0){
            return new ResultDTO(HttpCode.FAIL.getCode(), "更新失败");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"更新成功");
    }

    @Override
    public ResultDTO delete(int id) {
        if(0 == id){
            return new ResultDTO(HttpCode.FAIL.getCode(), "数据ID不能为空");
        }
        int insert = bookClassMapper.delete(id);
        if (insert <= 0){
            return new ResultDTO(HttpCode.FAIL.getCode(), "删除失败");
        }
        return new ResultDTO(HttpCode.SUCCESS.getCode(),"删除成功");
    }
}
