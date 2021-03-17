package com.example.demo.api.service;


import com.example.demo.api.book.BookClassDTO;
import com.example.demo.api.constants.ResultDTO;



/**
 * @author superman
 */
public interface BookClassService {
    /**
     * 根据名称模糊查询全部分类信息
     * @param name 分类名称
     * @return 匹配的数据集
     * */
    ResultDTO findListByName(String name);

    /**
     * 根据id查找
     * @param id 数据ID
     * @return 查找的数据集
     * */
    ResultDTO findById(int id);


    /**
     * 新增
     * @param entity 实体类不包含(ID)
     * @return 影响行数
     * */
    ResultDTO insert(BookClassDTO entity);

    /**
     * 更新
     * @param entity 实体类不包含(ID)
     * @return 影响行数
     * */
    ResultDTO update(BookClassDTO entity);

    /**
     * 更新
     * @param id 数据主键
     * @return 影响行数
     * */
    ResultDTO delete(int id);


}
