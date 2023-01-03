/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eli.peminjaman.service.service;

import com.eli.peminjaman.repository.PeminjamanRepository;
import com.eli.peminjaman.service.VO.Anggota;
import com.eli.peminjaman.service.VO.ResponseTemplateVO;
import com.eli.peminjaman.service.entity.Peminjaman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public Peminjaman savePeminjaman(Peminjaman peminjaman){
        return peminjamanRepository.save(peminjaman);
    }
    
    public ResponseTemplateVO getPeminjaman(Long peminjamanId){
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Peminjaman peminjaman = peminjamanRepository.findByPeminjamanId(peminjamanId);
        
        Anggota anggota = restTemplate.getForObject("http://localhost:9001/anggota"+peminjaman.getAnggotaId(), Anggota.class);
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        return vo;
    }
}
