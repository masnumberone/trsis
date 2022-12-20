package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.Literature;


public interface LiteratureService {

    Iterable<Literature> listAll();

    void delete(Integer id);

    Literature add(String source);

    Literature getById(Integer id);

    Literature put(Integer id, String source);


}
