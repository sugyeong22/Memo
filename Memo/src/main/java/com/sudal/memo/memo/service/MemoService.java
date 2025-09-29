package com.sudal.memo.memo.service;

import com.sudal.memo.memo.Repository.MemoRepositoy;
import com.sudal.memo.memo.domain.Memo;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepositoy memoRepositoy;

    public MemoService(MemoRepositoy memoRepositoy) {
        this.memoRepositoy = memoRepositoy;
    }

    public boolean createMemo(long userId, String title, String contents){

        Memo memo = Memo.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .build();

        try {
            memoRepositoy.save(memo);
        } catch (DataAccessException e){
            return false;
        }

        return true;
    }

    // 메모 리스트 조회
    public List<Memo>getMemoList(long userId){
        return memoRepositoy.findByUserId(userId, Sort.by("id").descending());
    }








}
