package com.scuse.mapper;

import com.scuse.entity.PaperTrack;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperTrackMapper {
    int insert(PaperTrack record);

    int insertSelective(PaperTrack record);
}