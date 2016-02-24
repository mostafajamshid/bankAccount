package com.mostafa.resource;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Produces("application/json")
public abstract class ResourceBase<T> {

    protected abstract List getAccountQuery() throws SQLException, NamingException;

    protected abstract List getAccountByQuery(String owner, String number, String type) throws SQLException, NamingException;

    protected abstract List getAccountByOwner(String owner) throws SQLException, NamingException;

    protected abstract List getAccountByNumber(String number) throws SQLException, NamingException;

    protected abstract List getAccountByType(String type) throws SQLException, NamingException;

    protected abstract List getSingleQuery(int id) throws NamingException;

    protected abstract void createQuery(T t) throws SQLException, NamingException;

    protected abstract void deleteQuery(int id) throws SQLException, NamingException;
    
    protected abstract void multipleDelete( List<String> ids) throws SQLException, NamingException;

    protected abstract void updateQuery(T t, int id) throws SQLException, NamingException;

    protected abstract String transferQuery(int from, int to, double amount) throws SQLException, NamingException;

    protected static EntityManager getEntityManager() throws NamingException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Account");
        return emf.createEntityManager();
    }

    @GET
    public List<T> getList() throws SQLException, NamingException {
        List records = getAccountQuery();
        return records;
    }

    @GET
    @Path("/query")
    public List<T> searchAccountByQuery(@Context UriInfo info) throws SQLException, NamingException {
        String accountOwner = info.getQueryParameters().getFirst("owner");
        String accountNumber = info.getQueryParameters().getFirst("number");
        String accountType = info.getQueryParameters().getFirst("type");
        List records = getAccountByQuery(accountOwner, accountNumber, accountType);
        return records;
    }

    @GET
    @Path("/byowner")
    public List<T> searchAccountByOwner(@Context UriInfo info) throws SQLException, NamingException {
        String accountOwner = info.getQueryParameters().getFirst("owner");
        List records = getAccountByOwner(accountOwner);
        return records;
    }

    @GET
    @Path("/bynumber")
    public List<T> searchAccountByNumber(@Context UriInfo info) throws SQLException, NamingException {
        String accountNumber = info.getQueryParameters().getFirst("number");
        List records = getAccountByNumber(accountNumber);
        return records;
    }

    @GET
    @Path("/bytype")
    public List<T> searchAccountByType(@Context UriInfo info) throws SQLException, NamingException {
        String accountType = info.getQueryParameters().getFirst("type");
        List records = getAccountByType(accountType);
        return records;
    }

    @GET
    @Path("{id}")
    public List<T> getSingle(@PathParam("id") int id) throws NamingException {
        List records = getSingleQuery(id);
        return records;
    }

    @POST
    public void insertAccount(T t) throws NamingException, SQLException {
        createQuery(t);
    }

    @DELETE
    @Path("{id}")
    public void deleteAccount(@PathParam("id") int id) throws SQLException, NamingException {
        deleteQuery(id);
    }

    

    @GET
    @Path("/delete")
    public void deleteAccounts(@Context UriInfo info) throws SQLException, NamingException {
        List<String> ids = info.getQueryParameters().get("id");
        multipleDelete(ids);
    }

    @PUT
    @Path("{id}")
    public void updateAccount(T t, @PathParam("id") int id) throws SQLException, NamingException {
        updateQuery(t, id);
    }

    @PUT
    @Path("/transfer")
    public Response transferAmount(@Context UriInfo info) throws SQLException, NamingException {
        String from = info.getQueryParameters().getFirst("from");
        String to = info.getQueryParameters().getFirst("to");
        String amount = info.getQueryParameters().getFirst("amount");
        String result = transferQuery(Integer.valueOf(from).intValue(), Integer.valueOf(to).intValue(), Double.valueOf(amount).doubleValue());
        if (result == "no") {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Accounts Type dose not match for account: " + from + " and " + to).build();
        }
        return Response.status(Response.Status.OK).entity("Transferred successfully").build();
    }

}
