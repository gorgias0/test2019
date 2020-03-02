package musicdatabase;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import domain.Artist;
import domain.Mediatype;
import domain.Media;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Lindkvist
 */
public class DBManager {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DEFAULT_PU");
    
    // Jag använder bara en instans av EntityManager för att undvika problem med att
    // ändringar inte syns i hela applikationen annars förrän man startar om.
    // Såvitt jag förstår behöver man inte tänka på trådsäkerhet så länge man är enda användaren av databasen.
    private final static EntityManager EM = EMF.createEntityManager();

    
    public <T> boolean execute(T entity, DBAction action) {
        try {
            EM.getTransaction().begin();
            switch (action) {
                case add : EM.persist(entity); break;
                case update : EM.merge(entity); break;
                case remove : EM.remove(EM.merge(entity)); break;
            }
            EM.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
          if (EM.getTransaction().isActive())
          EM.getTransaction().rollback();
        }
        return true;
    }
    
    public void close() {
        EM.close();
        EMF.close();
    }
    

    public Artist getArtist(long id) {
        return EM.find(Artist.class, id);
    }

    public Artist getArtistByName(String name) {
        TypedQuery<Artist> q = EM.createQuery("select a from Artist a where a.name = :name", Artist.class);
        q.setParameter("name", name);
        return q.getSingleResult();

    }
    
    public Mediatype getMediatype(long id) {
         return EM.find(Mediatype.class, id);
    }


    public List<Artist> getArtists() {
        List<Artist> artists = EM.createNativeQuery("select * from Artist;", Artist.class).getResultList();
        return artists;
    }

    public List<Media> getMedias() {
        List<Media> medias = EM.createNativeQuery("select * from Media;", Media.class).getResultList();
        return medias;
    }

    public List<Mediatype> getMediatypes() {
        List<Mediatype> mediatypes = EM.createNativeQuery("select * from Mediatype;", Mediatype.class).getResultList();
        return mediatypes;
    }
    
    
}

