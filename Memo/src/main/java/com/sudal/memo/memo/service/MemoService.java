package com.sudal.memo.memo.service;

import com.sudal.memo.common.FileManager;
import com.sudal.memo.memo.Repository.MemoRepositoy;
import com.sudal.memo.memo.domain.Memo;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class MemoService {

    private final MemoRepositoy memoRepositoy;

    public MemoService(MemoRepositoy memoRepositoy) {
        this.memoRepositoy = memoRepositoy;
    }

    public boolean createMemo(
            long userId,
            String title,
            String contents
            , MultipartFile file
    ){

        String imagePath = FileManager.saveFile(userId, file);

        Memo memo = Memo.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .imagePath(imagePath)
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

    public Memo getMemo(long id){
        Optional<Memo> optionalMemo = memoRepositoy.findById(id);
            return optionalMemo.get();
        }





    }
