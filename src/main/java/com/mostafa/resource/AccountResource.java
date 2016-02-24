package com.mostafa.resource;

import com.mostafa.entity.Account;
import java.sql.SQLException;
import static java.util.Collections.singletonList;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/account")
@Produces("application/json")
public class AccountResource extends ResourceBase<Account> {

    private EntityManager em;
    private List<Account> listAccounts;

    @Override
    protected List<Account> getAccountQuery() throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        listAccounts = em.createQuery("SELECT a FROM Account a").getResultList();
        em.getTransaction().commit();
        em.close();
        return listAccounts;
    }

    @Override
    protected List<Account> getAccountByQuery(String owner, String number, String type) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();

        listAccounts = em.createQuery(
                "SELECT a FROM Account a WHERE a.accountOwner LIKE :owner and a.accountNumber = :number and a.accountType = :type")
                .setParameter("owner", "%" + owner + "%")
                .setParameter("number", Integer.valueOf(number))
                .setParameter("type", Account.AccountType.valueOf(type))
                .getResultList();

        em.getTransaction().commit();
        em.close();
        return listAccounts;
    }

    @Override
    protected List<Account> getAccountByOwner(String owner) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();

        listAccounts = em.createQuery(
                "SELECT a FROM Account a WHERE a.accountOwner LIKE :owner")
                .setParameter("owner", "%" + owner + "%")
                .getResultList();

        em.getTransaction().commit();
        em.close();
        return listAccounts;
    }

    @Override
    protected List<Account> getAccountByNumber(String number) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();

        listAccounts = em.createQuery(
                "SELECT a FROM Account a WHERE a.accountNumber = :number")
                .setParameter("number", Integer.valueOf(number))
                .getResultList();

        em.getTransaction().commit();
        em.close();
        return listAccounts;
    }

    @Override
    protected List<Account> getAccountByType(String type) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();

        listAccounts = em.createQuery(
                "SELECT a FROM Account a WHERE  a.accountType = :type")
                .setParameter("type", Account.AccountType.valueOf(type))
                .getResultList();

        em.getTransaction().commit();
        em.close();
        return listAccounts;
    }

    @Override
    protected List getSingleQuery(int id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        listAccounts = singletonList(em.find(Account.class, id));
        em.getTransaction().commit();
        em.close();
        return listAccounts;
    }

    @Override
    protected void createQuery(Account a) throws SQLException, NamingException {
        Account account = new Account();
        em = getEntityManager();
        em.getTransaction().begin();
        account.setAccountOwner(a.getAccountOwner());
        account.setAccountType(a.getAccountType());
        account.setBalance(a.getBalance());
        em.persist(account);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void deleteQuery(int id) throws SQLException, NamingException {
        Account account = new Account();
        em = getEntityManager();
        em.getTransaction().begin();
        account = em.find(Account.class, id);
        em.remove(account);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected void  multipleDelete(List<String> ids) throws NamingException {
        Account account = new Account();
        em = getEntityManager();
        em.getTransaction().begin();
        for (String id : ids) {
          account = em.find(Account.class, Integer.valueOf(id).intValue());
          em.remove(account);
        }

        em.getTransaction().commit();
        em.close();
        
    }
    @Override
    protected void updateQuery(Account a, int id) throws SQLException, NamingException {
        Account account = new Account();
        em = getEntityManager();
        em.getTransaction().begin();
        account = em.find(Account.class, id);
        account.setAccountOwner(a.getAccountOwner());
        account.setAccountType(a.getAccountType());
        account.setBalance(a.getBalance());
        em.persist(account);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    protected String transferQuery(int from, int to, double amount) throws SQLException, NamingException {
        Account accountFrom = new Account();
        Account accountTo = new Account();
        em = getEntityManager();
        em.getTransaction().begin();
        accountFrom = em.find(Account.class, from);
        accountTo = em.find(Account.class, to);

        if (accountFrom.getAccountType().equals(accountTo.getAccountType())) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            em.persist(accountFrom);
            em.persist(accountTo);
            em.getTransaction().commit();
            em.close();
            return "ok";

        } else {
            return "no";
        }
    }
}
