package player.domain;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceRepository extends MongoRepository<Resource, String> {

    public List<Resource> findBySite(String site);

}
