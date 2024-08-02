package com.momentum.review.application;

import com.momentum.community.exception.CommunityPostException;
import com.momentum.global.exception.NotFoundException;
import com.momentum.review.domain.Doctor;
import com.momentum.review.domain.DoctorRepository;
import com.momentum.review.domain.DoctorTreatmentReviewPost;
import com.momentum.review.domain.DoctorTreatmentReviewPostRepository;
import com.momentum.review.domain.HospitalInfo;
import com.momentum.review.domain.HospitalInfoRepository;
import com.momentum.review.dto.request.CreateDoctorTreatmentReviewPostRequest;
import com.momentum.review.dto.response.GetAllDoctorTreatmentReviewPostResponse;
import com.momentum.review.dto.response.GetDoctorTreatmentReviewPostResponse;
import com.momentum.review.exception.DoctorException;
import com.momentum.review.exception.HospitalInfoException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DoctorTreatmentReviewService {

    private final DoctorTreatmentReviewPostRepository doctorTreatmentReviewPostRepository;
    private final HospitalInfoRepository hospitalInfoRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Long createDoctorTreatmentReviewPost(CreateDoctorTreatmentReviewPostRequest request) {
        HospitalInfo hospitalInfo = hospitalInfoRepository.findByHospital(request.hospital())
                .orElseThrow(() -> new NotFoundException(HospitalInfoException.NON_EXISTENT_HOSPITAL_INFORMATION));

        Doctor doctor = doctorRepository.findByNameAndHospitalInfo(request.doctor(), hospitalInfo)
                .orElse(
                        Doctor.builder()
                                .name(request.doctor())
                                .hospitalInfo(hospitalInfo)
                                .build()
                );
        doctorRepository.save(doctor);

        DoctorTreatmentReviewPost doctorTreatmentReviewPost = DoctorTreatmentReviewPost.builder()
                .hospital(request.hospital())
                .disease(request.disease())
                .treatment(request.treatment())
                .ageGroup(request.ageGroup())
                .doctorName(request.doctor())
                .doctor(doctor)
                .rating(request.rating())
                .title(request.title())
                .content(request.content())
                .hits(0)
                .likes(0)
                .dislikes(0)
                .build();
        return doctorTreatmentReviewPostRepository.save(doctorTreatmentReviewPost).getId();
    }

    @Transactional
    public GetDoctorTreatmentReviewPostResponse getDoctorTreatmentReviewPost(Long postId) {
        DoctorTreatmentReviewPost doctorTreatmentReviewPost = doctorTreatmentReviewPostRepository.findById(postId)
                .orElseThrow(() ->
                        new NotFoundException(CommunityPostException.NON_EXISTENT_DOCTOR_TREATMENT_REVIEW_POST)
                );
        doctorTreatmentReviewPost.increaseHits();
        return GetDoctorTreatmentReviewPostResponse.from(doctorTreatmentReviewPost);
    }

    @Transactional(readOnly = true)
    public List<GetAllDoctorTreatmentReviewPostResponse> getAllDoctorTreatmentReviewPosts(
            final Long hospitalId,
            final Long doctorId
    ) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException(DoctorException.NON_EXISTENT_DOCTOR));
        return doctorTreatmentReviewPostRepository.findAllByDoctor(doctor)
                .stream()
                .map(GetAllDoctorTreatmentReviewPostResponse::from)
                .toList();
    }

//    @Transactional(readOnly = true)
//    public List<GetDoctorTreatmentReviewPostTotalResponse> GetDoctorTreatmentReviewPostTotal() {
//        return doctorTreatmentReviewPostRepository.findAllByOrderByCreatedAtDesc()
//                .stream()
//                .map(GetDoctorTreatmentReviewPostTotalResponse::of)
//                .toList();
//    }
}
