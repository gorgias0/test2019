/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicdatabase;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import domain.Artist;
import domain.Mediatype;
import domain.Media;
import java.time.LocalDate;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Lindkvist
 */
public class DBManager {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("DEFAULT_PU");

    
    public <T> boolean execute(T entity, DBAction action) {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            switch (action) {
                case add : em.persist(entity); break;
                case update : em.merge(entity); break;
                case remove : em.remove(em.merge(entity)); break;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println(e);
            return false;
        } finally {
            em.close();
        }
        return true;
    }
    

    public Artist getArtist(long id) {
        EntityManager em = EMF.createEntityManager();
        return em.find(Artist.class, id);
    }

    public Artist getArtistByName(String name) {
        EntityManager em = EMF.createEntityManager();
        TypedQuery<Artist> q = em.createQuery("select a from Artist a where a.name = :name", Artist.class);
        q.setParameter("name", name);
        return q.getSingleResult();

    }
    
    public Mediatype getMediatype(long id) {
        EntityManager em = EMF.createEntityManager();
        return em.find(Mediatype.class, id);
    }


    public List<Artist> getArtists() {
        EntityManager em = EMF.createEntityManager();
        List<Artist> artists = em.createNativeQuery("select * from Artist;", Artist.class).getResultList();
        return artists;
    }

    public List<Media> getMedias() {
        EntityManager em = EMF.createEntityManager();
        List<Media> medias = em.createNativeQuery("select * from Media;", Media.class).getResultList();
        return medias;
    }

    public List<Mediatype> getMediatypes() {
        EntityManager em = EMF.createEntityManager();
        List<Mediatype> mediatypes = em.createNativeQuery("select * from Mediatype;", Mediatype.class).getResultList();
        return mediatypes;
    }
    
    
} ////// END CLASS //////////

    
//    public boolean persist(Mediatype m, DBAction action) {
//        EntityManager em = EMF.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            switch (action) {
//                case add : em.persist(m); break;
//                case update : em.merge(m); break;
//                case remove : em.remove(em.merge(m)); break;
//            }
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            System.err.println(e);
//            return false;
//        } finally {
//            em.close();
//        }
//        return true;
//    }
//    
//    
//    public boolean persist(Artist a, DBAction action) {
//        EntityManager em = EMF.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            switch (action) {
//                case add : em.persist(a); break;
//                case update : em.merge(a); break;
//                case remove : em.remove(em.merge(a)); break;
//            }
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            System.err.println(e);
//            return false;
//        } finally {
//            em.close();
//        }
//        return true;
//    }
//    public boolean persist(Media m, DBAction action) {
//        EntityManager em = EMF.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            switch (action) {
//                case add : em.persist(m); break;
//                case update : em.merge(m); break;
//                case remove : em.remove(em.merge(m)); break;
//            }
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//            return false;
//        } finally {
//            em.close();
//        }
//        return true;
//    }  
    
        
    
    
    
    
 //    public boolean updateMediatype(long id, String newName) {
//        EntityManager em = EMF.createEntityManager();
//        Mediatype mt = getMediatype(id);
//        try {
//            em.getTransaction().begin();
//            mt.setType(newName);
//            em.merge(mt);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        } finally {
//            em.close();
//        }
//        return true;
//    }

//    public void printAllMediatypes() {
//        EntityManager em = EMF.createEntityManager();
//        List<Mediatype> medias = em.createNativeQuery("select * from Mediatype;", Mediatype.class).getResultList();
//        medias.forEach(m -> System.out.println(m.getType()));
//        em.close();
//    }   
    
    //    public void printArtist() {
//        Artist a = getArtistByName("Bob Dylan");
//        System.out.println("Artist: "+ a);
//    }

//    public void testDeleteBobDylan() {
//        EntityManager em = EMF.createEntityManager();
//        em.getTransaction().begin();
//        Artist bob = getArtistByName("Bob Dylan");
//        em.remove(em.merge(bob));
//        em.getTransaction().commit();
//        em.close();
//    }

//    public void printArtistsByName() {
//        EntityManager em = EMF.createEntityManager();
//
//        TypedQuery q = em.createNamedQuery("Artist.findByName", Artist.class);
//        q.setParameter("name", "Carola");
//        System.out.println(q.getSingleResult());
//    }

//    public void createAndAddArtist() {
//        Artist a = new Artist();
//        a.setName("Carola");
//        addArtist(a);
//    }

//    public void createAndAddMedia() {
//        Media m = new Media();
//        m.setArtist(getArtist(1));
//        m.setCleaning_date(new java.util.Date());
//        m.setMediacondition(3);
//        m.setMediatype(getMediatype(1));
//        m.setRating(3);
//        m.setTitle("Testalbum 1");
//        m.setYear(1997);
//        addMedia(m);
//    }
    

