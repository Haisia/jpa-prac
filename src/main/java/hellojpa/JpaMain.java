package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {

      Member member1 = new Member();
      member1.setUsername("member1");
      em.persist(member1);


      em.flush();
      em.clear();

      Member m1 = em.find(Member.class, member1.getId());
      System.out.println("m1.getClass() = " + m1.getClass());

      Member reference = em.getReference(Member.class, member1.getId());
      System.out.println("reference.getClass() = " + reference.getClass());


      tx.commit();
    }catch(Exception e) {
      tx.rollback();
      e.printStackTrace();
    }finally {
      em.close();
    }
    emf.close();
  }

  private static void printMember(Member member) {
    System.out.println("member.getUsername() = " + member.getUsername());
  }

  private static void printMemberAndTeam(Member member) {
    String username = member.getUsername();
    System.out.println("username = " + username);


    Team team = member.getTeam();
    System.out.println("team = " + team.getName());
  }
}
