package com.clubAlbum.model;

import java.util.*;

public interface ClubAlbumDAO_interface {
          public void insert(ClubAlbumVO clubAlbumVO);
          public void update(ClubAlbumVO clubAlbumVO);
          //public void delete(Integer clubAlbumID);
          public ClubAlbumVO findByPrimaryKey(Integer clubAlbumID);
          public List<ClubAlbumVO> getAll();
}
