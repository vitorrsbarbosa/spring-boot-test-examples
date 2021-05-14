package com.barbosa.basics.nullable;

public class NullUser extends User {
    @Override
    public boolean isAuthenticated() {
        return false;
    }
}
