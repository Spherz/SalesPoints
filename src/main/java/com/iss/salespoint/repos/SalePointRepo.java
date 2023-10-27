package com.iss.salespoint.repos;

import com.iss.salespoint.entities.SalePoint;
import org.springframework.data.repository.CrudRepository;

public interface SalePointRepo extends CrudRepository<SalePoint, Long> {
}
