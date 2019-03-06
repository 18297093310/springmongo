package cn.jiestudy.springmongo.beans;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private int age;
    private String password;
}
