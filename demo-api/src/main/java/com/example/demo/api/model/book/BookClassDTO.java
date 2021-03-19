package com.example.demo.api.model.book;
import com.example.demo.api.model.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author superman
 */


@EqualsAndHashCode(callSuper = true)
@Data
public class BookClassDTO extends BaseDTO{

    /**
     * 分类名称
     */
    private String name;

}
