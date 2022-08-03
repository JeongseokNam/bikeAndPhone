package com.kong.bike.manager.service.impl;

import com.kong.bike.board.repository.BoardRepository;
import com.kong.bike.manager.service.ManagerService;
import com.kong.bike.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;




}

