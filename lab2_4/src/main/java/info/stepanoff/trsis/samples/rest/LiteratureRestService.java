package info.stepanoff.trsis.samples.rest;

import info.stepanoff.trsis.samples.db.model.Literature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import info.stepanoff.trsis.samples.service.LiteratureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/public/rest/literature")
public class LiteratureRestService {

    @Autowired
    private LiteratureService literatureService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(literatureService.listAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Integer id) {
        literatureService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        Literature literatureById = literatureService.getById(id);
        if (literatureById == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(literatureById);
        }
    }

    @RequestMapping(value = "/{source}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("source") String source) {
        return ResponseEntity.ok(literatureService.add(source));
    }

    @RequestMapping(value = "/{id}/{source}", method = RequestMethod.PUT)
    public ResponseEntity<Object> put(@PathVariable("id") Integer id, @PathVariable("source") String source) {
        return ResponseEntity.ok(literatureService.put(id, source));
    }


}
