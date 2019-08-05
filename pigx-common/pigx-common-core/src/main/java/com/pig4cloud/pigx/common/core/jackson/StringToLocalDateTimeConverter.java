package com.pig4cloud.pigx.common.core.jackson;


import cn.hutool.core.date.DatePattern;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author kylin
 * @create 2019-08-03 10:49
 */
@Component
public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(@Nullable String source) {
		return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
	}
}
