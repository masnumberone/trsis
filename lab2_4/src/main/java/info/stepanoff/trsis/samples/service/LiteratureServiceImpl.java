package info.stepanoff.trsis.samples.service;

import info.stepanoff.trsis.samples.db.model.Literature;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.stepanoff.trsis.samples.db.dao.LiteratureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.LongUnaryOperator;

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
        try {
            literatureRepository.deleteById(id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            //for the reason of idempotency leave this blank
        }
    }

    @Override
    public Literature add(String source) {
        return literatureRepository.save(new Literature(source));
    }

    @Override
    @Transactional
    public Literature put(Integer id, String source) {
        Literature liveLiterature = getById(id);
        if (liveLiterature != null) {
            liveLiterature.setSource(source);
            return liveLiterature;
        }
        else {
            return literatureRepository.save(new Literature(source));
        }

    }

    @Override
    public Literature getById(Integer id) {
        return literatureRepository.findById(id).orElse(null);
    }

}
