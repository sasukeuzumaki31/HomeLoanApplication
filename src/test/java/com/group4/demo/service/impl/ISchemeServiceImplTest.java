package com.group4.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.group4.demo.entity.Scheme;
import com.group4.demo.repository.ISchemeRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class ISchemeServiceImplTest {
    @MockBean
    private ISchemeRepository iSchemeRepository;

    @MockBean
    private ISchemeServiceImpl iSchemeServiceImpl;


    @Test
    void testGetAllSchemes() {
        ArrayList<Scheme> schemeList = new ArrayList<>();
        when(iSchemeServiceImpl.getAllSchemes()).thenReturn(schemeList);
        List<Scheme> actualAllSchemes = iSchemeServiceImpl.getAllSchemes();
        assertSame(schemeList, actualAllSchemes);
        assertTrue(actualAllSchemes.isEmpty());
        verify(iSchemeServiceImpl).getAllSchemes();
    }
}

