package com.momentum.domain.vo;

import com.momentum.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import java.util.Arrays;

public enum PostType {

    CONCERN,
    SURGERY_REVIEW,
    DAILY,
    DOCTOR_REVIEW,
    HOSPITAL_REVIEW;

    public static PostType getCommunityType(String communityType) {
        return Arrays.stream(PostType.values())
                .filter(it -> it.name().equals(communityType))
                .findAny()
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_COMMUNITY_TYPE));
    }
}
