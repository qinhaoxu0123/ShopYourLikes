package com.ucla.ShopYourLikes.service;

import com.ucla.ShopYourLikes.exception.BadRequestException;
import com.ucla.ShopYourLikes.model.Link;
import com.ucla.ShopYourLikes.model.User;
import com.ucla.ShopYourLikes.payload.LinkResponse;
import com.ucla.ShopYourLikes.payload.PagedResponse;
import com.ucla.ShopYourLikes.repository.LinkRepository;
import com.ucla.ShopYourLikes.repository.UserRepository;
import com.ucla.ShopYourLikes.util.AppConstants;
import com.ucla.ShopYourLikes.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import  org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(LinkService.class);
    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
    public PagedResponse<LinkResponse> getAllLinks(Object currentUser, int page, int size){
        validatePageNumberAndSize(page, size);
        User user = userRepository.findByUserId(Integer.parseInt(currentUser.toString()));

        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "creationDate");
        Page<Link> links = linkRepository.findByLinkIdUserId(user.getUserId(), pageable);
        if (links.getNumberOfElements() == 0){
            return new PagedResponse<>(Collections.emptyList(), links.getNumber(),
                    links.getSize(), links.getTotalElements(), links.getTotalPages(), links.isLast());
        }

        List<LinkResponse> linkResponses = links.map(link -> {
            return ModelMapper.mapLinkToLinkResponse(link);
        }).getContent();

        return new PagedResponse<>(linkResponses, links.getNumber(),
                links.getSize(), links.getTotalElements(), links.getTotalPages(), links.isLast());

    }
}