package ra.model.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ra.model.entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SongRepository implements ISongRepository {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.config.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Song> findAll() {
        List<Song> songs = new ArrayList<>();
        String hql = "SELECT s FROM Song AS s";
        TypedQuery<Song> type = entityManager.createQuery(hql, Song.class);
        songs = type.getResultList();
        return songs;
    }

    @Override
    public Song findByID(Long id) {
        TypedQuery<Song> type = entityManager.createQuery("SELECT s FROM Song AS s WHERE id = :id", Song.class);
        type.setParameter("id", id);
        Song song = type.getSingleResult();
        return song;
    }

    @Override
    public void save(Song s) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if(s.getId() == null){
                session.save(s);
            }else {
                Song old = findByID(s.getId());
                if(s.getFile_url() == null){
                    s.setFile_url(old.getFile_url());
                }
                old.clone(s);
                session.saveOrUpdate(old);
            }
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Session session =null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction= session.beginTransaction();
            session.delete(findByID(id));
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(transaction!=null){
                transaction.isActive();
            }
        }finally {
            if (session!=null){
                session.close();
            }
        }
    }
}
