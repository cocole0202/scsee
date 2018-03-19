package com.scuse.mapper;

import com.scuse.entity.Candidate;

public interface CandidateMapper {
    int insert(Candidate record);

    int insertSelective(Candidate record);
}