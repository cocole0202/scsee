package com.scuse.mapper;

import com.scuse.entity.PaperTrack;

public interface PaperTrackMapper {
    int insert(PaperTrack record);

    int insertSelective(PaperTrack record);
}