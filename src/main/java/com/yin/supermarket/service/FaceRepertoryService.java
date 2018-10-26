package com.yin.supermarket.service;

import com.yin.supermarket.dao.IFaceRepository;
import com.yin.supermarket.entity.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaceRepertoryService {
    @Autowired
    IFaceRepository iFaceRepository;

    public List<Face> getAllFace() {
        return iFaceRepository.findAll();
    }
}
