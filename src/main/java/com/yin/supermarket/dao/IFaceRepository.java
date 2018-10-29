package com.yin.supermarket.dao;

import com.yin.supermarket.entity.Face;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFaceRepository extends MongoRepository<Face,String> {

//   List<Face> findByName(String name_or_id_num);
//
//   List<Face> findById_num(String name_or_id_num);

}
