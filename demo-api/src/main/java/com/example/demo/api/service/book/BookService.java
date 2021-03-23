package com.example.demo.api.service.book;

import com.example.demo.api.model.book.BookDTO;
import com.example.demo.api.constants.ResultDTO;

public interface BookService {
    /**
     * 根据名称模糊查询全部书籍信息
     * @param name  分类名称
     * @return 匹配的数据集
     */
    ResultDTO findListByName(String name);

    /**
     * 根据ID查找数据
     * @param id 数据ID
     * @return 查找的数据集
     */
    ResultDTO findById(int id);

    /**
     * 新增
     * @param entity 实体类(不包含ID)
     * @return 影响行数
     */
    ResultDTO insert(BookDTO entity);

    /**
     * 更新
     * @param entity 实体类(包含ID)
     * @return  影响行数
     */
    ResultDTO update(BookDTO entity);

    /**
     * 删除
     * @param id 数据主键
     * @return 影响行数
     */
    ResultDTO delete(int id);
}
