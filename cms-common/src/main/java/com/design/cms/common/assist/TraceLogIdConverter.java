package com.design.cms.common.assist;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.design.cms.common.utils.TraceLogIdUtils;

public class TraceLogIdConverter extends ClassicConverter {
	
	@Override
	public String convert(ILoggingEvent event) {
		return event.getMDCPropertyMap().get(TraceLogIdUtils.traceLogId);
	}
}
