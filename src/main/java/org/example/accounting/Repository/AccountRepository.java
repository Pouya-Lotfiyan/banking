package org.example.accounting.Repository;


import org.example.accounting.models.Account;
import org.example.base.BaseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository implements BaseRepository<Account> {

    private SessionFactory sessionFactory;

    public AccountRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Account updateOne(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            account = (Account) session.merge(account);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return account;
    }

    @Override
    public Account findById(long id) {
        Account account = null;
        Session session = sessionFactory.openSession();
        try {
            account = (Account) session.get(Account.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            accounts = (ArrayList<Account>) session.createQuery("from Account").list();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return accounts;
    }

    @Override
    public Account deleteOne(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            account.setEnable(false);
            session.update(account);
            transaction.commit();
        }finally {
            session.close();
        }

        return account;
    }

    @Override
    public Account save(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long id = 0;
        try {
            id = (long) session.save(account);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        account.setId(id);
        return account;
    }

    public List<Account> findByAccountNumber(long accountNumber) {
        ArrayList<Account> accounts = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            accounts = (ArrayList<Account>) session.createQuery("from Account a where a.accountNumber = :accountNumber")
                    .setParameter("accountNumber", accountNumber)
                    .list();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return accounts;
    }
}
