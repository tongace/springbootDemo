package com.dxc.application.mybatis.mapper;

import java.util.List;

import com.dxc.application.model.GimHeader;

public interface GimHeaderMapper {
	 public List<GimHeader> selectAllGimHeader();
	 public GimHeader selectGimHeaderByGimType(GimHeader gimHeader);
}
