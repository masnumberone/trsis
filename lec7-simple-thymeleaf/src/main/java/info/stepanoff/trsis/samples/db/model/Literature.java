/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */

package info.stepanoff.trsis.samples.db.model;

import javax.persistence.*;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Pavel
 */
@Entity
@Table(name = "Literature")
@Data
public class Literature {

    public Literature() {
    }

    public Literature(Integer nomer, String name) {
        this.number = nomer;
        this.name = name;
    }

    @Id
    @Column(name = "literature_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "literature_number", unique = true)
    private Integer number;

    @Column(name = "literature_name")
    private String name;
}
