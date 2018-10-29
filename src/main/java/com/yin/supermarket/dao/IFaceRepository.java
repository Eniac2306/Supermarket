package com.yin.supermarket.dao;

import com.yin.supermarket.entity.Face;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IFaceRepository extends MongoRepository<Face,String> {

   List<Face> findByName(String name);

   List<Face> findByCardNum(String cardNum);

}
