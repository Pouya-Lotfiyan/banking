package org.example.accounting.Repository;

import org.example.accounting.models.Account;
import org.example.accounting.models.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DocumentRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public DocumentRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    public List<Document> findByDocumentNumber(long documentNumber) {
        ArrayList<Document> documents = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            documents = (ArrayList<Document>) session.createQuery("from Document d where d.documentNumber = :documentNumber")
                    .setParameter("documentNumber", documentNumber)
                    .list();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return documents;


    }

    public Document save(Session transferMoneySession, Document document) {
            try {
                long id = (long) transferMoneySession.save(document);
                transferMoneySession.getTransaction().commit();
                document.setId(id);
                return document;
            }catch (Exception e){
                transferMoneySession.getTransaction().rollback();
                transferMoneySession.close();
                throw new BadRequestException("something went wrong.");
            }
    }
}
