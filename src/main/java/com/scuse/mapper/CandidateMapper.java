package com.scuse.mapper;

import com.scuse.entity.Candidate;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateMapper {
    int insert(Candidate record);

    int insertSelective(Candidate record);

    Candidate selectByToken(String token);

    Candidate selectByPhone(String phone);

    Candidate selectByMail(String mail);

    Candidate selectByIdNum(String idNum);

    Candidate selectById(int id);

    int getMaxID();

    int updateByPrimaryKeySelective(Candidate candidate);

    List<Candidate> getAll();
}