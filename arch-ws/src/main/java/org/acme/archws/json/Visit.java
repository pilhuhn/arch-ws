package org.acme.archws.json;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Das Business-Objekt
 */
@Entity
public class Visit extends PanacheEntity {

    // Keine id nötig, da von Panache bereitgestellt
    // public long id;
    public String name;
    public Date registriert = new Date();

}
