package com.group4.demo.service;

import com.group4.demo.Dto.SchemeDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Scheme;

import java.util.List;

public interface ISchemeService {
    List<Scheme> getAllSchemes();
    Scheme getSchemeById(int schemeid) throws ResourceNotFoundException;
    Scheme addScheme(SchemeDto schemeDto);
    Scheme deleteSchemeById(int id) throws ResourceNotFoundException;
    Scheme updateScheme(int id,SchemeDto schemeDto) throws ResourceNotFoundException;
}
