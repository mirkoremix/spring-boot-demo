package com.united.demo.repositories;

import com.united.demo.models.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section, Long> {
}
