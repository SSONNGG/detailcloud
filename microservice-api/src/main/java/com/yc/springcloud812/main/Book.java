package com.yc.springcloud812.main;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data      //  lombok 注解，节省getter, setter
@Table(name="book")   // JPA注解,将java类关联表
public class Book {
    @Id  // JPA注解,指定此属性为表中的主键
    private Integer bookId;   //注意: 对应的数据表中的字段名叫 book_id
    private String bookName;
    private String bookAuthor;
    private BigDecimal bookPrice;
    private Date bookDate;
    private Integer userId;
}
