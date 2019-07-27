package com.kasoft.register.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kasoft.register.base.entity.DoctorDepartmentdictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 科室字典
 *
 * @author charlie
 * @date 2019-04-30 17:10:43
 */
@Mapper
public interface DoctorDepartmentdictionaryMapper extends BaseMapper<DoctorDepartmentdictionary> {
	/**
	 * 根据医院编号获取科室列表
	 * @param hospitalId
	 * @return
	 */
	List<DoctorDepartmentdictionary> getDepartmentDictByHospital(@Param("hospitalId") String hospitalId);
}
