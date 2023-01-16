package many_to_one_uni_bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import many_to_one_uni_bank.dto.Bank;
import many_to_one_uni_bank.dto.Branch;
import many_to_one_uni_stdscl.dto.School;

public class BranchDao {
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		return entityManagerFactory.createEntityManager();
	}

	public void saveBranch(Branch branch)
	{
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();
		Bank bank= branch.getBank();
		entityManager.persist(bank);
		entityManager.persist(branch);
		entityTransaction.commit();

	}
	public void updateBranch(int id, Branch branch) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Branch newbranch = entityManager.find(Branch.class, id);
		if (newbranch != null) {
			branch.setId(id);
			branch.setBank(newbranch.getBank());
			entityTransaction.begin();
			entityManager.merge(branch);
			entityTransaction.commit();
		} else
			System.out.println("Branch not exists");
	}
	public void deleteBranch(int id) {
		EntityManager entityManager = getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Branch newbranch = entityManager.find(Branch.class, id);
		entityTransaction.begin();
		entityManager.remove(newbranch);
		entityTransaction.commit();

	}

	public void getBranchById(int id) {
		EntityManager entityManager = getEntityManager();

		Branch newbranch= entityManager.find(Branch.class, id);
		System.out.println(newbranch);

	}

	public void getAllBank() {
		EntityManager entityManager = getEntityManager();

		Query query = entityManager.createQuery("SELECT b from Branch b");
		List<Branch> branchlist = query.getResultList();
		System.out.println(branchlist);

	}

}
