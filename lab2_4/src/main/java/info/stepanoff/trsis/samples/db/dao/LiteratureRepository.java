package info.stepanoff.trsis.samples.db.dao;

import info.stepanoff.trsis.samples.db.model.Literature;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavel
 */
public interface LiteratureRepository extends CrudRepository<Literature, Integer> {

    // List<Literature> findByNumber(Integer number);
}
