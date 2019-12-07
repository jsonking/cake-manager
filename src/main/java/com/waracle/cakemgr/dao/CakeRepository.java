package com.waracle.cakemgr.dao;

import com.waracle.cakemgr.model.Cake;
import org.springframework.data.repository.CrudRepository;

public interface CakeRepository extends CrudRepository<Cake, Integer> {
}
