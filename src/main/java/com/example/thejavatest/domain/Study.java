package com.example.thejavatest.domain;

import com.example.thejavatest.StudyStatus;

public class Study {

    private StudyStatus status;

    private int limit;

    private String name;

    private Member owner;

    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }

        this.status = StudyStatus.DRAFT;
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public int getLimit() {
        return limit;
    }

    public void setOwner(Member member) {
        this.owner = member;
    }

    public Member getOwner() {
        return this.owner;
    }
}
