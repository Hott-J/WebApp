package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext // 스프링부트가 이 애노테이션을 보고 엔티티매니저를 주입. 스프링부트가 알아서 앤티티매니저도 만들어줌.
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId(); // member를 리턴하지 않고 Id를 리턴하는 이유는 스타일. 저장하고나면 사이드이팩트를 줄 수 있으므로 리턴값 거의 안만듦.
    }

    public Member find(Long id){
        return em.find(Member.class,id);
    }
}
