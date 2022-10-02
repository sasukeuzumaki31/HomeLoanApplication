package com.group4.demo.service.impl;

import com.group4.demo.Dto.SchemeDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ISchemeRepository;
import com.group4.demo.service.ISchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISchemeServiceImpl implements ISchemeService {

    @Autowired
    ISchemeRepository schemeRepo;

    @Autowired
    ISchemeServiceImpl schemeService;

    @Override
    public List<Scheme> getAllSchemes() {
        return schemeRepo.findAll();
    }

    @Override
    public Scheme getSchemeById(int schemeid) throws ResourceNotFoundException{
        return schemeRepo.findById(schemeid).orElseThrow(() -> new ResourceNotFoundException("Scheme does not exists with id : " + schemeid));
    }

    @Override
    public Scheme addScheme(SchemeDto schemeDto) {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(schemeDto.getInterestRate());
        scheme.setTenure(schemeDto.getTenure());
        return schemeRepo.save(scheme);
    }

    @Override
    public Scheme deleteSchemeById(int schemeid) throws ResourceNotFoundException{
        Scheme scheme = schemeRepo.findById(schemeid).orElseThrow(()->new ResourceNotFoundException("Scheme does not exists with id : " + schemeid));
        schemeRepo.delete(scheme);
        return scheme;
    }

    @Override
    public Scheme updateScheme(int id, SchemeDto schemeDto) throws ResourceNotFoundException{
        Scheme schemeOp = schemeRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Scheme does not exists with id : " + id));
            schemeOp.setInterestRate(schemeDto.getInterestRate());
            schemeOp.setTenure(schemeDto.getTenure());
            return schemeRepo.save(schemeOp);
    }
}
