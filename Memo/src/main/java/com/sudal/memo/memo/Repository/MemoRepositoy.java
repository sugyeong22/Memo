package com.sudal.memo.memo.Repository;

// jpa 기반으로

import com.sudal.memo.memo.domain.Memo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepositoy extends JpaRepository<Memo, Long> {

    // WHERE `userId` = #{}
    public List<Memo> findByUserId(long userId, Sort sort);




}
