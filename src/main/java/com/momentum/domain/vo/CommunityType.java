package com.momentum.domain.vo;

import com.momentum.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import java.util.Arrays;

public enum CommunityType {

    CONCERN,
    SURGERY_REVIEW,
    DAILY,
    NONE,
    ;

    public static CommunityType getCommunityType(String communityType) {
        return Arrays.stream(CommunityType.values())
                .filter(it -> it.name().equals(communityType))
                .findAny()
                .orElseThrow(() -> new NotFoundException(CommunityPostException.NON_EXISTENT_COMMUNITY_TYPE));
    }
}
