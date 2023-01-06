package com.example.demo.Model.Services;

import com.example.demo.Model.Entities.User;
import com.example.demo.Model.Entities.UserCruises;
import com.example.demo.Model.Exceptions.Wishlist.UserCruisesNotFound;
import com.example.demo.Model.Repositories.UserCruiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private UserCruiseRepository userCruiseRepository;

    public List<UserCruises> allRows()
    {

        return userCruiseRepository.findAll();
    }
    public List<Long> listCurrentUserWishlist(Long id)
    {
        if(userCruiseRepository.findIdByUserId(id).isEmpty())
        {
            throw new UserCruisesNotFound(id);
        }else
        {
            return userCruiseRepository.findIdByUserId(id);
        }
    }
    public List<Long> getIdByUserIdAndCruiseId(Long uid, Long cid)
    {
        return userCruiseRepository.findIdByUserIdAndCruiseId(uid,cid);
    }
    public boolean checkIfExists(Long uid, Long cid)
    {
        if(userCruiseRepository.findIdByUserIdAndCruiseId(uid,cid).isEmpty())
        {
            return false;
        }else
        {
            return true;
        }
    }
}
