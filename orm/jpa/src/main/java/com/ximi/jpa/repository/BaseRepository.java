package com.ximi.jpa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();

}
