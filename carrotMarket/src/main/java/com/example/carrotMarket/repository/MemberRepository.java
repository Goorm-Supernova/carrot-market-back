package com.example.carrotMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carrotMarket.entity.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
