/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.dao.LiteratureRepository;
import info.stepanoff.trsis.samples.db.model.Literature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LiteratureServiceImpl implements LiteratureService {

    @Autowired
    private LiteratureRepository literatureRepository;

    @Override
    public Iterable<Literature> findAll() {
        return literatureRepository.findAll();
    }

    @Override
    public void save(Literature literature) {
        literatureRepository.save(literature);
    }

    @Override
    public void delete(Integer id) { literatureRepository.deleteById(id);}

}
