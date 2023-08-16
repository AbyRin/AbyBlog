package com.cpr.cprblog.repository;

import com.cpr.cprblog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
    @Query(value="select * from member where email=?1",nativeQuery = true)
    /* sql的'?': 占位符，替代参数位置 */
    Member findByEmail(String email);
}
