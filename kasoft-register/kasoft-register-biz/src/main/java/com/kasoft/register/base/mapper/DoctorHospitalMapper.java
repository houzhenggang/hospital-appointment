package com.kasoft.register.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kasoft.register.base.api.entity.DoctorHospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 医院
 *
 * @author charlie
 * @date 2019-04-30 17:09:57
 */
@Mapper
public interface DoctorHospitalMapper extends BaseMapper<DoctorHospital> {
	/**
	 * 根据名称查询医院
	 * @param name 名称
	 * @return
	 */
	List<DoctorHospital> getHospitalByName(@Param("name") String name);
}
