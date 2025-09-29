package com.sudal.memo.memo;

import com.sudal.memo.memo.domain.Memo;
import com.sudal.memo.memo.service.MemoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/memo")
@RestController
public class MemoRestConroller {

    private final MemoService memoService;

    public MemoRestConroller(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping("/write-process")
    public Map<String, String> writeMemo(
            @RequestParam String title
            , @RequestParam String contents
            , HttpSession session
    ){
        long userId = (Long) session.getAttribute("userId");

        Map<String, String> resultMap = new HashMap<>();

        if(memoService.createMemo(userId, title, contents)){
            resultMap.put("result", "success");
        } else{
            resultMap.put("result", "fail");
        }

        return resultMap;
    }









}
