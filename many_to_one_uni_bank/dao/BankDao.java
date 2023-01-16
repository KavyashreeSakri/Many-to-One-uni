package many_to_one_uni_bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import many_to_one_uni_bank.dto.Bank;

public class BankDao {

	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		return entityManagerFactory.createEntityManager();
	}

	public void saveBank(Bank bank) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		entityManager.persist(bank);
		entityTransaction.commit();

	}

	public void updateBank(int id, Bank bank) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Bank newbank = entityManager.find(Bank.class, id);
		if (newbank != null) {
			bank.setId(id);
			entityTransaction.begin();
			entityManager.merge(bank);
			entityTransaction.commit();
		} else
			System.out.println("Bank not exists");
	}

	public void deleteBank(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Bank newbank = entityManager.find(Bank.class, id);
		entityTransaction.begin();
		entityManager.remove(newbank);
		entityTransaction.commit();

	}

	public void getSchoolById(int id) {
		EntityManager entityManager = getEntityManager();

		Bank newbank = entityManager.find(Bank.class, id);
		System.out.println(newbank);

	}

	public void getAllBank() {
		EntityManager entityManager = getEntityManager();

		Query query = entityManager.createQuery("SELECT b from Bank b");
		List<Bank> banklist = query.getResultList();
		System.out.println(banklistF);

	}

}
