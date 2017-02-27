package com.design.cms.dao.persist;

import com.design.cms.dao.entity.Attachment;


public interface AttachmentMapper {
	
	void insert(Attachment attachment);

	Attachment getAttachmentById(Long id);

	int update(Attachment attachment);
	
}
