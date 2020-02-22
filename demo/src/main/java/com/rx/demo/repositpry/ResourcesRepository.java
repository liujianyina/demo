package com.rx.demo.repositpry;


import com.rx.demo.domain.Resources;

import java.util.List;

public interface ResourcesRepository extends BaseRepository<Resources> {

    List<Resources> findAllByDisplay(int display);

    List<Resources> findAllByType(int type);

}
