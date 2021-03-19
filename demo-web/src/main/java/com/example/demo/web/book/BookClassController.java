package com.example.demo.web.book;

import com.example.demo.api.model.book.BookClassDTO;
import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.enums.HttpCode;
import com.example.demo.api.service.book.BookClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author superman
 */
@RestController
@RequestMapping(value = "/bookClass")
public class BookClassController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookClassService bookClassService;

    /**
     * 根据名称查找分类信息
     * */
    @RequestMapping("/findListByName")
    public ResultDTO findListByName(String name){
        try{
            return bookClassService.findListByName(name);
        }catch (Exception e){
            logger.error("系统异常:"+e);
            return new ResultDTO(HttpCode.EXCEPTION.getCode(),"系统异常");
        }
    }

    /**
     * 根据ID查找分类信息
     * */
    @RequestMapping("/findById")
    public ResultDTO findById(int id){
        try{
            return bookClassService.findById(id);
        }catch (Exception e){
            logger.error("系统异常:"+e);
            return new ResultDTO(HttpCode.EXCEPTION.getCode(),"系统异常");
        }
    }

    /**
     * 插入信息
     * */
    @RequestMapping("/insert")
    public ResultDTO insert(BookClassDTO bookClassDTO){
        try{
            return bookClassService.insert(bookClassDTO);
        }catch (Exception e){
            logger.error("系统异常:"+e);
            return new ResultDTO(HttpCode.EXCEPTION.getCode(),"系统异常");
        }
    }

    /**
     * 更新
     * */
    @RequestMapping("/update")
    public ResultDTO update(BookClassDTO bookClassDTO){
        try{
            return bookClassService.update(bookClassDTO);
        }catch (Exception e){
            logger.error("系统异常:"+e);
            return new ResultDTO(HttpCode.EXCEPTION.getCode(),"系统异常");
        }
    }

    /**
     * 删除
     * */
    @RequestMapping("/delete")
    public ResultDTO delete(int id){
        try{
            return bookClassService.delete(id);
        }catch (Exception e){
            logger.error("系统异常:"+e);
            return new ResultDTO(HttpCode.EXCEPTION.getCode(),"系统异常");
        }
    }


}
