package top.whr.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.whr.userservice.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
