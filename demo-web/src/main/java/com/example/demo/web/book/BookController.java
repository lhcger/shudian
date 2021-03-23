package com.example.demo.web.book;

import com.example.demo.api.model.book.BookDTO;
import com.example.demo.api.constants.ResultDTO;
import com.example.demo.api.enums.HttpCode;
import com.example.demo.api.service.book.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class
BookController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private  BookService bookService;

    /**
 * 根据名称查找分类信息
 *
 * @param
 */
        @RequestMapping("/findListByName")
        public ResultDTO findListByName(String name) {
            try {
                return bookService.findListByName(name);
            } catch (Exception e) {
                logger.error("系统异常：" + e);
                return new ResultDTO(HttpCode.EXCEPTION.getCode(), "系统异常");
            }
        }

        /**
         * 根据Id查找数据
         *
         * @param
         */
        @RequestMapping("/findById")
        public ResultDTO findById( int id) {
            try {
                return bookService.findById(id);
            } catch (Exception e) {
                logger.error("系统异常：" + e);
                return new ResultDTO(HttpCode.EXCEPTION.getCode(), "系统异常");
            }
        }

        /**
         * 新增
         *
         * @param bookDTO 新增实体
         */
        @RequestMapping("/insert")
        public ResultDTO insert(BookDTO bookDTO) {
            try {
                return bookService.insert(bookDTO);
            } catch (Exception e) {
                logger.error("系统异常：" + e);
                return new ResultDTO(HttpCode.EXCEPTION.getCode(), "系统异常");
            }
        }

        /**
         * 新增
         *
         * @param bookDTO 更新实体
         */
        @RequestMapping("/update")
        public ResultDTO update( BookDTO bookDTO) {
            try {
                return bookService.update(bookDTO);
            } catch (Exception e) {
                logger.error("系统异常：" + e);
                return new ResultDTO(HttpCode.EXCEPTION.getCode(), "系统异常");
            }
        }

        /**
         * 根据Id删除数据
         *
         * @param
         */
        @RequestMapping("/delete")
        public ResultDTO delete(int id) {
            try {
                return bookService.delete(id);
            } catch (Exception e) {
                logger.error("系统异常：" + e);
                return new ResultDTO(HttpCode.EXCEPTION.getCode(), "系统异常");
            }
        }
    }