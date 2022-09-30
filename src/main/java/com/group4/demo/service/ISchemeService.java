package com.group4.demo.service;

import com.group4.demo.Dto.SchemeDto;
import com.group4.demo.entity.Scheme;

import java.util.List;

public interface ISchemeService {
    List<Scheme> getAllSchemes();
    Scheme getSchemeById(int schemeid);
    Scheme addScheme(SchemeDto schemeDto);
    Scheme deleteSchemeById(int id);
    Scheme updateScheme(int id,SchemeDto schemeDto);
}
