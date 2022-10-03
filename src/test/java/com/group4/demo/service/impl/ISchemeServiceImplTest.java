package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.Dto.SchemeDto;
import com.group4.demo.advices.ResourceNotFoundException;
import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ISchemeRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ISchemeServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ISchemeServiceImplTest {
    @MockBean
    private ISchemeRepository iSchemeRepository;

    @Autowired
    private ISchemeServiceImpl iSchemeServiceImpl;

    @Test
    void testGetAllSchemes() {
        ArrayList<Scheme> schemeList = new ArrayList<>();
        when(iSchemeRepository.findAll()).thenReturn(schemeList);
        List<Scheme> actualAllSchemes = iSchemeServiceImpl.getAllSchemes();
        assertSame(schemeList, actualAllSchemes);
        assertTrue(actualAllSchemes.isEmpty());
        verify(iSchemeRepository).findAll();
    }

    @Test
    void testGetSchemeById() throws ResourceNotFoundException {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        Optional<Scheme> ofResult = Optional.of(scheme);
        when(iSchemeRepository.findById(any())).thenReturn(ofResult);
        assertSame(scheme, iSchemeServiceImpl.getSchemeById(1));
        verify(iSchemeRepository).findById(any());
    }

    @Test
    void testGetSchemeById2()  {
        when(iSchemeRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iSchemeServiceImpl.getSchemeById(1));
        verify(iSchemeRepository).findById(any());
    }

    @Test
    void testAddScheme() {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        when(iSchemeRepository.save( any())).thenReturn(scheme);
        assertSame(scheme, iSchemeServiceImpl.addScheme(new SchemeDto(123, 10.0d, 1)));
        verify(iSchemeRepository).save(any());
    }

    @Test
    void testDeleteSchemeById() throws ResourceNotFoundException {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        Optional<Scheme> ofResult = Optional.of(scheme);
        doNothing().when(iSchemeRepository).delete( any());
        when(iSchemeRepository.findById( any())).thenReturn(ofResult);
        assertSame(scheme, iSchemeServiceImpl.deleteSchemeById(1));
        verify(iSchemeRepository).findById( any());
        verify(iSchemeRepository).delete( any());
    }

    @Test
    void testDeleteSchemeById2() {
        doNothing().when(iSchemeRepository).delete( any());
        when(iSchemeRepository.findById( any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> iSchemeServiceImpl.deleteSchemeById(1));
        verify(iSchemeRepository).findById(any());
    }

    @Test
    void testUpdateScheme() throws ResourceNotFoundException {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        Optional<Scheme> ofResult = Optional.of(scheme);

        Scheme scheme1 = new Scheme();
        scheme1.setInterestRate(10.0d);
        scheme1.setSchemeId(123);
        scheme1.setTenure(1);
        when(iSchemeRepository.save( any())).thenReturn(scheme1);
        when(iSchemeRepository.findById(any())).thenReturn(ofResult);
        assertSame(scheme1, iSchemeServiceImpl.updateScheme(1, new SchemeDto(123, 10.0d, 1)));
        verify(iSchemeRepository).save(any());
        verify(iSchemeRepository).findById(any());
    }

    @Test
    void testUpdateScheme2() {
        Scheme scheme = new Scheme();
        scheme.setInterestRate(10.0d);
        scheme.setSchemeId(123);
        scheme.setTenure(1);
        when(iSchemeRepository.save(any())).thenReturn(scheme);
        when(iSchemeRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> iSchemeServiceImpl.updateScheme(1, new SchemeDto(123, 10.0d, 1)));
        verify(iSchemeRepository).findById(any());
    }


}

