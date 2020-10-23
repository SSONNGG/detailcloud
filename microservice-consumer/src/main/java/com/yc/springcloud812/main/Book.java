package com.yc.springcloud812.main;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data      //  lombok 注解，节省getter, setter
public class Book {

    private Integer bookId;   //注意: 对应的数据表中的字段名叫 book_id
    private String bookName;
    private String bookAuthor;
    private BigDecimal bookPrice;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date bookDate;
    private Integer userId;
}
