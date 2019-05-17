package com.chaoliu1995.assistant.mapper;

import com.chaoliu1995.assistant.dto.CommonListDTO;
import com.chaoliu1995.assistant.entity.CommonSet;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2019/4/16 11:45
 */
@Component("commonSetMapper")
public interface CommonSetMapper extends Mapper<CommonSet> {
    /**
     *
     * @param listDTO
     * @return
     */
    List<CommonSet> listByCommonListDTO(@Param("listDTO") CommonListDTO listDTO,@Param("typeKey") Integer typeKey);

    /**
     *
     * @param listDTO
     * @return
     */
    int countByCommonListDTO(@Param("listDTO") CommonListDTO listDTO,@Param("typeKey") Integer typeKey);
}
