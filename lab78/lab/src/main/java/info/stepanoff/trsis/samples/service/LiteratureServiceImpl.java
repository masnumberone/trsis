/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.dao.LiteratureRepository;
import info.stepanoff.trsis.samples.db.model.Literature;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LiteratureServiceImpl implements LiteratureService {


    @Autowired
    private LiteratureRepository literatureRepository;

    @Override
    public Iterable<Literature> listAll() {
        return literatureRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        literatureRepository.deleteById(id);
    }

    @Override
    public Literature add(Integer number, String name) {
        return literatureRepository.save(new Literature(number, name));
    }

    @Override
    public Literature findByNumber(Integer number) {
        return literatureRepository.findByNumber(number);
    }
}
