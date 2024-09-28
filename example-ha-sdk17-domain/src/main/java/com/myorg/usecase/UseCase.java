package com.myorg.usecase;

import com.myorg.kernel.command.Command;

@FunctionalInterface
public interface UseCase<T extends Command, R> {
    R execute(T command);
}
