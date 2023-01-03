//package com.bondarenko.movieland.repository;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//@Repository
//public class UserRepositor {
//
//
//    private final List<UserDetails> APPLICATION_USERS = Arrays.asList(
//            new User(
//                    "user",
//                    "pass",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
//            ),
//            new User(
//                    "admin.gmail",
//                    "password",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//            )
//    );
//
//    public UserDetails loadUserByEmail(String email) {
////        com.bondarenko.movieland.entity.User user = findUserByEmail(email).get();
////        APPLICATION_USERS.add(new User(
////                email,
////                user.getPassword(),
////                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
//
//        return APPLICATION_USERS
//                .stream()
//                .filter(u -> u.getUsername().equals(email))
//                .findFirst()
//                .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
//    }
//}