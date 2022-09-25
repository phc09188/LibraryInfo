package com.example.libraryproject.DB;

import com.example.libraryproject.Domain.Lib;
import com.example.libraryproject.Service.LibService;

import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {
        LibService a = new LibService();
        List<Lib> list = a.search("소");
        for(Lib lib : list){
            System.out.println(lib.getLbrryNm());
        }
    }
}
