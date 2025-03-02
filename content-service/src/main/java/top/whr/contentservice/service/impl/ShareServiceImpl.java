package top.whr.contentservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.whr.contentservice.entity.Share;
import top.whr.contentservice.mapper.ShareMapper;
import top.whr.contentservice.service.ShareService;

@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {
    @Override
    public Share getShareById(Integer id) {
        return this.getById(id);
    }
}
