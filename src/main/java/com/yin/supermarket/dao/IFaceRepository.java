package com.yin.supermarket.dao;
import com.yin.supermarket.entity.Face;
import com.yin.supermarket.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IFaceRepository extends MongoRepository<Face,Long> {


}
