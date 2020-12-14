package com.example.demo.api.model.book;

import com.example.demo.api.model.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
/**
 * @author superman
 */

@Data
public class BookClassDTO extends BaseDTO implements Serializable {

    /**
     * 分类名称
     */
    private String name;
}
