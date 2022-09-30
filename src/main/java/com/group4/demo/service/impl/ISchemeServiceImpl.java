package com.group4.demo.service.impl;

import com.group4.demo.Dto.SchemeDto;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ISchemeRepository;
import com.group4.demo.service.ISchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ISchemeServiceImpl implements ISchemeService {

    @Autowired
    ISchemeRepository schemeRepo;

    @Override
    public List<Scheme> getAllSchemes() {
        return schemeRepo.findAll();
    }

    @Override
    public Scheme getSchemeById(int schemeid) {
        Optional<Scheme> scheme = schemeRepo.findById(schemeid);
        if(scheme.isPresent()){
            return scheme.get();
        }
        return null;
    }

    @Override
    public Scheme addScheme(SchemeDto schemeDto) {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(schemeDto.getInterestRate());
        scheme.setTenure(schemeDto.getTenure());
        return schemeRepo.save(scheme);
    }

    @Override
    public Scheme deleteSchemeById(int schemeid) {
        Optional<Scheme> scheme = schemeRepo.findById(schemeid);
        if(scheme.isPresent()){
            schemeRepo.delete(scheme.get());
            return scheme.get();
        }
        return null;
    }

    @Override
    public Scheme updateScheme(int id,SchemeDto schemeDto) {
        Optional<Scheme> schemeOp = schemeRepo.findById(id);
        if(schemeOp.isPresent()){
            Scheme schemeObj = schemeOp.get();
            schemeObj.setInterestRate(schemeDto.getInterestRate());
            schemeObj.setTenure(schemeDto.getTenure());
            return  schemeRepo.save(schemeObj);
        }
        return null;
    }
}
