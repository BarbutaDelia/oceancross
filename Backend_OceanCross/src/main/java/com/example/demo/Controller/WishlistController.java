package com.example.demo.Controller;


import com.example.demo.Model.Entities.Cruise;
import com.example.demo.Model.Entities.UserCruises;
import com.example.demo.Model.Exceptions.Wishlist.UserCruisesNotFound;
import com.example.demo.Model.Repositories.UserCruiseRepository;
import com.example.demo.Model.Services.CruiseService;
import com.example.demo.Model.Services.WishlistService;
import com.example.demo.Model.Util.Conversion;
import com.example.demo.View.DTOs.CruiseDto;
import com.example.demo.View.DTOs.Payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController
{
    @Autowired
    UserCruiseRepository userCruisesRepository;
    @Autowired
    CruiseService cruiseService;


    @Autowired
    WishlistService wishlistService;

    @GetMapping("")
    public ResponseEntity<?>getAllFromWishlist()
    {
        List<UserCruises> usersCruises=wishlistService.allRows();
        if(usersCruises.isEmpty())
        {
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usersCruises,HttpStatus.OK);

    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getCurrentUserWishlist(@PathVariable Long user_id)
    {
        try
        {
            List<Long> userCruisesIds=wishlistService.listCurrentUserWishlist(user_id);
            List<Cruise> cruises =cruiseService.listSpecifiedCruises(userCruisesIds);
            List<CruiseDto> cruiseDtos =Conversion.getCruiseDtosFromCruises(cruises);
            return new ResponseEntity<>(cruiseDtos, HttpStatus.OK);
        }
        catch (UserCruisesNotFound e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{user_id}/{cruise_id}")
    public ResponseEntity<?> addCruiseToWishlist(@PathVariable Long user_id,@PathVariable Long cruise_id)
    {

            if(wishlistService.checkIfExists(user_id,cruise_id))
            {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: The cruise is already in wishlist"));
            }
            else
            {
                UserCruises userCruise=new UserCruises();
                userCruise.setUserId(user_id);
                userCruise.setCruiseId(cruise_id);
                userCruisesRepository.save(userCruise);
                return ResponseEntity.ok(new MessageResponse("Cruise added to current user wishlist"));
            }

        }



}
