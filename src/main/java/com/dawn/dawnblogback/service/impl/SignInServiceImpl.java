package com.dawn.dawnblogback.service.impl;

import com.dawn.dawnblogback.mapper.SignInMapper;
import com.dawn.dawnblogback.pojo.SignIn;
import com.dawn.dawnblogback.service.SignInService;
import com.dawn.dawnblogback.service.UserService;
import com.dawn.dawnblogback.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    private SignInMapper signInMapper;
    @Autowired
    private UserService userService;

    @Override
    public Integer getContinueDays() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        SignIn signIn = isSignedIn();
        if (signIn == null)
            return 0;
        Integer num =  signInMapper.getSignInByUserId(userId).getContinueDays();
        return num;
    }

    @Override
    public Integer getReward() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        // 获取奖励
        SignIn signIn = isSignedIn();
        Integer reward = signIn.getContinueDays();
        if (reward > 7)
            reward = 7;
        userService.updateCoin(userId, reward);
        return reward;
    }

    @Override
    public void update(SignIn signIn) {
        signInMapper.update(signIn);
    }

    @Override
    public void add() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        signInMapper.add(userId);
    }

    @Override
    public SignIn isSignedIn() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return signInMapper.getSignInByUserId(userId);
    }

    @Override
    public List<SignIn> list() {
        List<SignIn> list = signInMapper.list();
        return list;
    }
}
