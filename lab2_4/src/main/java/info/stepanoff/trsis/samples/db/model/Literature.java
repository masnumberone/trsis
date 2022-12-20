package info.stepanoff.trsis.samples.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "Literature")

public class Literature implements Serializable {

    private static final long serialVersionUID = 1L;

    public Literature() {
    }

    public Literature(Integer id, String source) {
        this.id = id;
        this.source = source;
    }

    public Literature(String source) {
        this.source = source;
    }

    @Id
    @Column(name = "literature_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


    @Column(name = "literature_source")
    private String source;

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
}
