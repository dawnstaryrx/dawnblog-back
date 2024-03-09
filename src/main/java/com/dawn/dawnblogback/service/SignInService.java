package com.dawn.dawnblogback.service;

import com.dawn.dawnblogback.pojo.SignIn;

public interface SignInService {
    SignIn isSignedIn();

    void add();

    void update(SignIn signIn);

    void getReward();
}
