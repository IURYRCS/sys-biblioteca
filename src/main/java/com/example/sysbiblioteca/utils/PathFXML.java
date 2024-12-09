package com.example.sysbiblioteca.utils;

import java.nio.file.Paths;

public class PathFXML {

    public static String pathBase() {
        return Paths.get("C:\\Users\\Iury\\Desktop\\ENG. SOFTWARE\\sys-biblioteca\\src\\main\\java\\com\\example\\sysbiblioteca\\view").toAbsolutePath().toString();
    }
}
