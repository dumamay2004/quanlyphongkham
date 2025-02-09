package com.example.quanlybenhvien.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.quanlybenhvien.Dao.VaiTroDao;
import com.example.quanlybenhvien.Entity.Vaitro;


@Service
public class VaiTroService {
    private final VaiTroDao vaiTroDao;
    public VaiTroService(VaiTroDao vaiTroDao)
    {
        this.vaiTroDao = vaiTroDao;
    }

    public List<Vaitro> findAll()
    {
        return vaiTroDao.findAll();
    }
    public Vaitro findVaitroByID(String maVT)
    {
        return vaiTroDao.findById(maVT).orElse(null);
    }
    public void saveVaiTro(Vaitro vaitro)
    {
        vaiTroDao.save(vaitro);
    }
    public void deleteVaiTro(String maVT)
    {
        vaiTroDao.deleteById(maVT);
    }
}
