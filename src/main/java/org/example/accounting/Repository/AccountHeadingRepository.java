package org.example.accounting.Repository;

import org.example.accounting.models.AccountHeading;
import org.example.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountHeadingRepository implements BaseRepository<AccountHeading> {


    private SessionFactory sessionFactory;


    @Autowired
    public AccountHeadingRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public List<AccountHeading> findAll() {
        ArrayList<AccountHeading> accountHeadings = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
           accountHeadings = (ArrayList<AccountHeading>) session.createQuery("from AccountHeading ah where ah.enable = true").list();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return accountHeadings;
    }

    public AccountHeading save(AccountHeading accountHeading) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(accountHeading);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return accountHeading;

    }


    public List<AccountHeading> findByCode(long code){
        List<AccountHeading> accountHeadings = new ArrayList();
        Session session = sessionFactory.openSession();
        try {
            accountHeadings = session.createQuery("from AccountHeading ah where ah.code = :code")
                    .setParameter("code", code).list();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return accountHeadings;
    }


    public AccountHeading findById(long id) {
        AccountHeading accountHeading = new AccountHeading();
        Session session = sessionFactory.openSession();
        try {
            accountHeading = session.get(AccountHeading.class, id);
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

        return accountHeading;

    }

    public AccountHeading updateOne(AccountHeading accountHeading) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            accountHeading = (AccountHeading) session.merge(accountHeading);
            transaction.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return accountHeading;
    }

    public AccountHeading deleteOne(AccountHeading accountHeading) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            accountHeading.setEnable(false);
            session.update(accountHeading);
            transaction.commit();
        }finally {
            session.close();
        }

        return accountHeading;

    }
}
