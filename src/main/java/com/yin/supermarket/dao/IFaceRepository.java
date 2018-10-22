package com.yin.supermarket.dao;

import com.yin.supermarket.entity.Face;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFaceRepository extends MongoRepository<Face,String> {


}
