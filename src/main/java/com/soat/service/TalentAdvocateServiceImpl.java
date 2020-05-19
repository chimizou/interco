package com.soat.service;

import com.soat.dao.TalentAdvocateRepository;
import com.soat.entities.TalentAdvocate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("talentAdvocateService")
@Transactional
public class TalentAdvocateServiceImpl implements TalentAdvocateService {

    @Autowired
    TalentAdvocateRepository talentAdvocateRepository;

    public List<TalentAdvocate> getTalentAdvocates() {
        return talentAdvocateRepository.findAll();
    }
}
