package com.example.service.impl.userdetailsimpl;

import com.example.data.entity.TaiKhoan;
import com.example.data.repository.TaiKhoanRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.data.repository.PhanQuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private PhanQuyenRepository phanQuyenRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(userName).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("email: ", userName))
        );

        return UserDetailsImpl.build(taiKhoan);
    }

}