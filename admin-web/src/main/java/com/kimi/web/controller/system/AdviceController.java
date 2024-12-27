package com.kimi.web.controller.system;

import com.kimi.common.pojo.entity.Advice;
import com.kimi.system.mapper.AdviceMapper;
import com.kimi.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/advice/")
public class AdviceController {
    @Autowired
    private AdviceMapper adviceMapper;
    @PostMapping("commit")
    public Result commit(@RequestBody Advice advice) {
        StringBuilder text = new StringBuilder();
        for (String feedbackReason : advice.getFeedbackReasons()) {
            text.append(feedbackReason+",");
        }
        advice.setFeedbackReasonString(text.toString());
        advice.setCreateTime(new Date());
        adviceMapper.insert(advice);
        return Result.success();
    }
}
