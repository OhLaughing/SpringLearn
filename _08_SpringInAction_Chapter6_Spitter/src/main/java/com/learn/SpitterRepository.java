package com.learn;


public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
