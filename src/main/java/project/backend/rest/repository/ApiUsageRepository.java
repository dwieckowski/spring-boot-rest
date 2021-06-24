package project.backend.rest.repository;

import org.springframework.data.repository.CrudRepository;
import project.backend.rest.entity.ApiUsage;

public interface ApiUsageRepository extends CrudRepository<ApiUsage, String> {
}
