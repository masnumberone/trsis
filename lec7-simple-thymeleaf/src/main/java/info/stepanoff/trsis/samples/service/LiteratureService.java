/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.Literature;

public interface LiteratureService {

    Iterable<Literature> findAll();

    void save(Literature literature);

    void delete(Integer id);
}
