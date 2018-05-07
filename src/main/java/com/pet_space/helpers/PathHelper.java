package com.pet_space.helpers;

import com.pet_space.models.essences.UserEssence;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

public final class PathHelper {
    private PathHelper() {
    }

    @NotNull
    public static String getPath(Authentication authentication, String nickname, Supplier<UserEssence> supplier) {
        if (authentication.getName().equals(nickname)) {
            return supplier.get().getRole().toString().toLowerCase();
        } else return "redirect:/profile/" + nickname;
    }
}
