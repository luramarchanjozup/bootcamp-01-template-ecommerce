package com.zup.mercadolivre.model.enums;

public enum Profiles {
    
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_CLIENT");

    private int code;
    private String description;

    private Profiles(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public static Profiles toEnum(Integer code) {
        if(code == null) {
            return null;
        }

        for(Profiles x : Profiles.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id: " + code);
    }
}
