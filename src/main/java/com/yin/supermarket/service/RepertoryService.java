package com.yin.supermarket.service;

import com.yin.supermarket.dao.IFaceRepository;
import com.yin.supermarket.entity.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepertoryService {
    @Autowired
    IFaceRepository iFaceRepository;

    public List<Face> getAllFace() {
        return iFaceRepository.findAll();
    }

    public List<Face> selectedFace(String name_or_id_num) {
        if (iFaceRepository.findByName(name_or_id_num) != null) {
            return iFaceRepository.findByName(name_or_id_num);
        } else if (iFaceRepository.findByCardNum(name_or_id_num) != null) {
            return iFaceRepository.findByCardNum(name_or_id_num);
        } else {
            return null;
        }
    }

}
