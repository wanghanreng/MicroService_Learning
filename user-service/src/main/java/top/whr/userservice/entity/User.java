package top.whr.userservice.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {
    @TableId
    private Integer id;
    private String mobile;
    private String password;
    private String userName;
    private String avatarUrl;
}
