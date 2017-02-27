package com.design.cms.service.api.vo.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResp {
	private Long attachmentId;
	private String fileName;
	private String classify;
	private String serverFileName;
	private String filePath;
}
