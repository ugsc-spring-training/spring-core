package com.example.democore.proxy;

import java.lang.reflect.Proxy;

public class ProxyExample {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        nameRepository.saveName("Miso");

        Repository proxy = (Repository)Proxy.newProxyInstance(
                ProxyExample.class.getClassLoader(),
                new Class[]{Repository.class},
                (proxy1, method, args1) -> {

                        args1[0] = "Fero";
//                        con.beginTransation();

//                        try {
                        return method.invoke(nameRepository, args1);
//                        } catch() {
//                            rollback;
//                        }
//
//                        commit;
                }
        );


        proxy.saveName("Jano");
    }
}

class NameRepository implements Repository {

    @Override
    public void saveName(String name) {
        // DB save;
        System.out.println("saved: " + name);
    }
}

interface Repository {
    public void saveName(String name);
}
