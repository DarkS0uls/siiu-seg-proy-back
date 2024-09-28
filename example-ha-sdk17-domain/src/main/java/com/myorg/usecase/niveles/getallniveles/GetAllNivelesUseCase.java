package com.myorg.usecase.niveles.getallniveles;

import com.myorg.kernel.command.niveles.GetAllNivelesCommand;
import com.myorg.kernel.domain.in.niveles.GetAllNivelesInformation;
import com.myorg.kernel.exception.error.UseCaseErrorResponse;
import com.myorg.usecase.UseCase;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class GetAllNivelesUseCase implements UseCase<GetAllNivelesCommand, Either<UseCaseErrorResponse, GetAllNivelesInformation>> {

    @Override
    public Either<UseCaseErrorResponse, GetAllNivelesInformation> execute(GetAllNivelesCommand command) {
        return null;
    }
}
