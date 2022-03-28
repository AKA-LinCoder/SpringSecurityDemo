package com.lsaac.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsaac.login.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

}
