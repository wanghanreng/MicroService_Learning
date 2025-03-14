package top.whr.userservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Entity;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("t_user")
public class User {
    @TableId
    private Integer id;
    private String mobile;
    private String password;
    private String userName;
    private String avatarUrl;

    @TableField(value= "update_time" , fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value= "create_time" , fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
